package io.my.habitat.microservice.account;

import io.my.habitat.microservice.account.api.RestUserAccountAPIVerticle;
import io.my.habitat.microservice.account.impl.JdbcAccountServiceImpl;
import io.my.habitat.microservice.common.BaseMicroserviceVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;


/**
 * A verticle publishing the user service.
 *
 * @author Eric Zhao
 */
public class UserAccountVerticle extends BaseMicroserviceVerticle {

  private AccountService accountService;

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    // create the service instance
    accountService = new JdbcAccountServiceImpl(vertx, config());
    // register the service proxy on event bus
    ProxyHelper.registerService(AccountService.class, vertx, accountService, AccountService.SERVICE_ADDRESS);
    // publish the service and REST endpoint in the discovery infrastructure
    publishEventBusService(AccountService.SERVICE_NAME, AccountService.SERVICE_ADDRESS, AccountService.class)
      .compose(servicePublished -> deployRestVerticle())
      .setHandler(future.completer());
  }

  private Future<Void> deployRestVerticle() {
    Future<String> future = Future.future();
    vertx.deployVerticle(new RestUserAccountAPIVerticle(accountService),
      new DeploymentOptions().setConfig(config()),
      future.completer());
    return future.map(r -> null);
  }
}
