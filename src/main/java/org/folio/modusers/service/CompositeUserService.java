package org.folio.modusers.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.data.util.CastUtils.cast;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.folio.modusers.client.CredentialsClient;
import org.folio.modusers.client.PermsClient;
import org.folio.modusers.dto.CompositeUserDto;
import org.folio.modusers.dto.CompositeUserListObjectDto;
import org.folio.modusers.dto.CredentialsDto;
import org.folio.modusers.dto.FullPermissions;
import org.folio.modusers.dto.PermissionUser;
import org.folio.modusers.dto.PermissionUserDto;
import org.folio.modusers.dto.UserDto;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompositeUserService {

  private static final String EXPAND_PERMS_INCLUDE = "expandPerms";
  private static final String CREDENTIALS_INCLUDE = "credentials";
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

  public CompositeUserDto getUserById(String userId, List<String> include, boolean expandPerms) {
    UserDto userById = userService.getUserById(userId);
    CompositeUserDto dto = new CompositeUserDto();
    dto.setUser(userById);
    Map<String, CompletableFuture> list = new HashMap<>();
    if (include.contains(PERMS_INCLUDE)) {
      list.put(PERMS_INCLUDE, async(() -> enrichPermissions(dto, userId)));
    }
    if (include.contains(CREDENTIALS_INCLUDE)) {
      list.put(CREDENTIALS_INCLUDE,
          async(() -> async(() -> enrichCredentials(dto, userId))));
    }
    if (expandPerms) {
      list.put(EXPAND_PERMS_INCLUDE,
          async(() -> enrichFullPermissions(dto, cast(list.get(PERMS_INCLUDE).join()))));
    }
    list.values().forEach(CompletableFuture::join);
    return dto;
  }

  public CompositeUserListObjectDto getUsers(int offset, int limit, List<String> include) {
    List<UserDto> userDtos = userService.getUsersByOffset(offset, limit);
    List<CompositeUserDto> compositeUserDtos = userDtos.stream().map(userDto -> {
      CompositeUserDto compositeUserDto = new CompositeUserDto();
      compositeUserDto.setUser(userDto);
      return compositeUserDto;
    }).collect(Collectors.toList());
    Map<String, List<CompletableFuture>> list = new HashMap<>();
    if (include.contains(PERMS_INCLUDE)) {
      list.put(PERMS_INCLUDE,
          asyncPermsList(compositeUserDtos));
    }
    if (include.contains(CREDENTIALS_INCLUDE)) {
      list.put(CREDENTIALS_INCLUDE,
          asyncCredsList(compositeUserDtos));
    }
    list.values().stream().flatMap(Collection::stream).forEach(CompletableFuture::join);
    CompositeUserListObjectDto compositeUserListObjectDto = new CompositeUserListObjectDto();
    compositeUserListObjectDto.setCompositeUsers(compositeUserDtos);
    return compositeUserListObjectDto;
  }

  private PermissionUserDto enrichPermissions(CompositeUserDto dto, String userId) {
    PermissionUserDto perms = permsClient.getPerms("userId==" + userId);
    dto.setPermissions(perms);
    return perms;
  }

  private PermissionUserDto enrichFullPermissions(CompositeUserDto user,
      List<PermissionUser> permissionUser) {
    FullPermissions fullPerms = permsClient.getFullPerms(permissionUser.get(0).getId());
    PermissionUserDto permissionUserDto = new PermissionUserDto();
    permissionUserDto.setPermissions(cast(fullPerms.getPermissionNames()));
    user.setPermissions(permissionUserDto);
    return permissionUserDto;
  }

  private CredentialsDto enrichCredentials(CompositeUserDto dto, String userId) {
    CredentialsDto credentials = credentialsClient.get("userId==" + userId);
    dto.setCredentials(credentials);
    return credentials;
  }


  private <T> CompletableFuture<T> async(Supplier<T> jobSupplier) {
    return supplyAsync(jobSupplier, executor);
  }

  private List<CompletableFuture> asyncPermsList(List<CompositeUserDto> list) {
    return list.stream()
        .map(e -> async(() -> enrichPermissions(e, e.getUser().getId())))
        .collect(Collectors.toList());
  }

  private List<CompletableFuture> asyncCredsList(List<CompositeUserDto> list) {
    return list.stream()
        .map(e -> async(() -> enrichCredentials(e, e.getUser().getId())))
        .collect(Collectors.toList());
  }

  private static ExecutorService newLimitedCachedThreadPool(int threadNum, String name) {
    return new ThreadPoolExecutor(0, threadNum,
        20L, TimeUnit.SECONDS, new SynchronousQueue<>(),
        new CustomizableThreadFactory(name + "-exec-"));
  }

}
