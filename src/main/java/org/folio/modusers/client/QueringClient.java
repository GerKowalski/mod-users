package org.folio.modusers.client;

import java.util.List;
import liquibase.pro.packaged.E;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.krb5.Credentials;

public interface QueringClient<E> {

  E get(@RequestParam String query);

}
