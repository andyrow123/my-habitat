package io.my.habitat.microservice.room;

import io.my.habitat.microservice.common.BaseMicroserviceVerticle;
import io.my.habitat.microservice.room.api.RestRoomAPIVerticle;
import io.my.habitat.microservice.room.impl.RoomCRUDServiceImpl;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;

import static io.my.habitat.microservice.room.RoomCRUDService.SERVICE_ADDRESS;
import static io.my.habitat.microservice.room.RoomCRUDService.SERVICE_NAME;

/**
 * A verticle for room operation (e.g. apply or close) processing.
 *
 * @author Eric Zhao
 */
public class RoomVerticle extends BaseMicroserviceVerticle {

  private RoomCRUDService crudService;

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    crudService = new RoomCRUDServiceImpl(vertx, config());
    ProxyHelper.registerService(RoomCRUDService.class, vertx, crudService, SERVICE_ADDRESS);
    // publish service and deploy REST verticle
    publishEventBusService(SERVICE_NAME, SERVICE_ADDRESS, RoomCRUDService.class)
      .compose(servicePublished -> deployRestVerticle(crudService))
      .setHandler(future.completer());
  }

  private Future<Void> deployRestVerticle(RoomCRUDService service) {
    Future<String> future = Future.future();
    vertx.deployVerticle(new RestRoomAPIVerticle(service),
      new DeploymentOptions().setConfig(config()),
      future.completer());
    return future.map(r -> null);
  }
}
