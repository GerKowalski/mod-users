package org.folio.modusers.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OkapiHeaders {

  /**
   * The common "X-Okapi" prefix for all the headers defined here.
   *
   */
  public static final String PREFIX = "X-Okapi";

  /** X-Okapi-Token. A token that identifies the user who is making the current
   * request. May carry additional permissions and other stuff related to
   * authorization. Only the authorization modules should look inside. For the
   * rest of the system this should be opaque. When a module needs to make a
   * call to another module, it needs to pass the token it received in its
   * request into the request to the new module.
   */
  public String token;

  /** X-Okapi-Additional-Token. A token that identifies allows auth
   * to give privilege on behalf as other user (sudo)
   */
  public String additionalToken;

  /**
   * X-Okapi-Url. Tells the URL where the modules may contact Okapi, for making
   * requests to other modules. Can be set on Okapi's command line when starting
   * up. Note that it may point to some kind of load balancer or other network
   * trickery, but in the end there will be an Okapi instance listening on it.
   * Does not include a trailing slash. Defaults to http://localhost:9130
   */
  public String url;

  /**
   * X-Okapi-Url-to. Like X-Okapi-Url but is the instance of the new service
   * when calling a system interface (/_/tenant in particular)
   */
  public String urlTo;

  /** X-Okapi-Tenant. Tells which tenant is making the request. Every request
   * to Okapi should have this header set to a valid tenant ID. When making a
   * call from one module to another, remember to copy this over.
   */
  public String tenant;

  /**
   * X-Okapi-User-Id. Tells the user id of the logged-in user. Modules can pass
   * this around, but that is not necessary if we have a good token,
   * mod-authtoken extracts the userId from the token and returns it to Okapi,
   * and Okapi passes it to all modules it invokes.
   */
  public String USER_ID;

  /** X-Okapi-Trace. Will be added to the responses from Okapi, to help
   * debugging
   * where the request actually went, and how long did it take. For example "GET
   * sample-module-1.0.0 http://localhost:9231/testb : 204 3748us"
   */
  public String trace;

  /**
   * X-Okapi-Module-Id. Explicit call to a given module. Used to distinguish
   * which of the 'multiple' type modules we mean to call. Not to be used in
   * regular requests.
   */
  public String moduleId;

  /**
   * X-Okapi-Request-Id. Identifies the original request to Okapi. Useful for
   * logging.
   *
   */
  public String requestId;

  /**
   * X-Okapi-Permissions. The permissions a module expressed interest in, and
   * which were granted to this user. Can be used for modifying the way a module
   * behaves.
   */
  public String permissions;

  /**
   * X-Okapi-Stop. A signal from a module to Okapi to stop the pipeline
   * processing and return the result immediately. Only to be used in special
   * circumstances, like in the filters like auth.
   */
  public String stop;

  /*
   The rest are only used internally, in Okapi, or between Okapi and the
   auth complex or the post filter.
   */
  /**
   * X-Okapi-Filter. Passed to filters (but not real handlers). Tells which
   * phase we are in. Also contains the path pattern that matched, separated by
   * one space. For example 'X-Okapi-Filter: auth /foo'. (In some rare cases,
   * like with old-fashioned ModuleDescriptors, it may be plain 'auth')
   */
  public String filter;
  /**
   * X-Okapi-request info. Passed to pre/post filters.
   */
  public String requestIp;
  public String requestTimestamp;
  public String requestMethod;

  /**
   * X-Okapi-Match-Path-Pattern. Path pattern that has matched when invoking a handler
   */
  public String matchPathPattern;

  /**
   * X-Okapi-Permissions-Required. Lists the permissions a given module
   * requires. Used only between Okapi and the auth complex.
   */
  public String permissionsRequired;

  /** X-Okapi-Permissions-Desired. Lists the permissions a given module is
   * interested in, without strictly needing them.
   * Used only between Okapi and the auth complex.
   */
  public String permissionsDesired;

  /** X-Okapi-Module-Permissions. Permissions granted to a module.
   * Used only between Okapi and the authorization module.
   */
  public String modulePermissions;

  /**
   * X-Okapi-Extra-Permissions. Additional permissions granted by Okapi itself.
   * Used only between Okapi and the authorization module, in some special
   * situations, like when a moduleDescriptor has a "redirect" routing entry and
   * also module-specific permissions.
   */
  public String extraPermissions;

  /** X-Okapi-Module-Tokens. JWT tokens specifically made for invoking given
   * modules.
   * Used only between Okapi and the authorization module.
   */
  public String moduleTokens;

  /**
   * X-Okapi-Auth-Result. Used for passing the HTTP result code of the auth filter
   * to the post filter(s).
   */
  public String authResult;

  /**
   * X-Okapi-Auth-Headers. Used for passing the HTTP headers response of the auth filter
   * to the post filter(s).
   */
  public String authHeaders;

  /**
   * X-Okapi-Handler-Result. Used for passing the HTTP result code of the actual
   * handler to the post filter(s).
   */
  public String handlerResult;

  /**
   * X-Okapi-Handler-Headers. Used for passing the HTTP headers response of the handler
   * to the post filter(s).
   */
  public String handlerHeaders;

}
