package org.folio.modusers.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.folio.modusers.client.CredentialsClient;
import org.folio.modusers.client.PermsClient;
import org.folio.modusers.dto.CompositeUserDto;
import org.folio.modusers.dto.FullPermissions;
import org.folio.modusers.dto.PermissionUser;
import org.folio.modusers.dto.PermissionUserDto;
import org.folio.modusers.dto.UserDto;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBusinessService {

  private static final String EXPAND_PERMS_INCLUDE = "expandPerms";
  private static String CREDENTIALS_INCLUDE = "credentials";
  private static final String PERMS_INCLUDE = "perms";

  private final UserService userService;
  private final PermsClient permsClient;

  private final CredentialsClient credentialsClient;

  private ExecutorService executor;
  private int threadPoolSize = 10;

  @PostConstruct
  public void init() {
    executor = newLimitedCachedThreadPool(threadPoolSize, "service");
  }

  public void getBlUsersByIdById(String userId, List<String> include, boolean expandPerms) {
    UserDto userById = userService.getUserById(userId);
    CompositeUserDto dto = new CompositeUserDto();
    Map<String, CompletableFuture> list = new HashMap<>();
    if (include.contains(PERMS_INCLUDE)) {
      list.put(PERMS_INCLUDE, async(() -> enrichPermissions(dto, userId)));
    }
    if (expandPerms) {
      list.put(EXPAND_PERMS_INCLUDE, async(
          () -> enrichFullPermissions(dto, cast(list.get(PERMS_INCLUDE).join()))));
    }
    if (include.contains(CREDENTIALS_INCLUDE)) {
      list.put(CREDENTIALS_INCLUDE, async(
          () -> credentialsClient.get("userId==" + userId)));
    }


  }

  private PermissionUserDto enrichFullPermissions(CompositeUserDto user, List<PermissionUser> permissionUser) {
    FullPermissions fullPerms = permsClient.getFullPerms(permissionUser.get(0).getId());
    PermissionUserDto permissionUserDto = new PermissionUserDto();
    permissionUserDto.setPermissions(cast(fullPerms.getPermissionNames()));
    user.setPermissions(permissionUserDto);
    return permissionUserDto;
  }

  private PermissionUserDto enrichPermissions(CompositeUserDto dto, String userId) {
    PermissionUserDto perms = permsClient.getPerms("userId==" + userId);
    dto.setPermissions(perms);
    ;
    return perms;
  }

  private <T> void getAndMap(T perms, Consumer<T> permsMapper) {
    permsMapper.accept(perms);
  }


  private <T> CompletableFuture<T> async(Supplier<T> jobSupplier) {
    return supplyAsync(jobSupplier, executor);
  }


  public static <T> T getFuture(CompletableFuture<T> completableFuture) {
    return completableFuture.join();
  }

  public static ExecutorService newLimitedCachedThreadPool(int threadNum, String name) {
    return new ThreadPoolExecutor(0, threadNum,
        20L, TimeUnit.SECONDS, new SynchronousQueue<>(),
        new CustomizableThreadFactory(name + "-exec-"));
  }

  /**
   * This method can be used by code that is deliberately violating the allowed checked casts.
   * Rather than marking the whole method containing the code with @SuppressWarnings, you can use a
   * call to this method for the exact place where you need to escape the constraints.  Typically
   * you will "import static" this method and then write either X x = cast(y); or, if that doesn't
   * work (e.g. X is a type variable) Util.&lt;X&gt;cast(y);
   */
  @SuppressWarnings("unchecked")
  public static <T> T cast(Object o) {
    return (T) o;
  }

}
