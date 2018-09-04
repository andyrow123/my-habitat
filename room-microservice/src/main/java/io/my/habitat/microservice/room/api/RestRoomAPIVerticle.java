package io.my.habitat.microservice.room.api;

import io.my.habitat.microservice.common.RestAPIVerticle;
import io.my.habitat.microservice.room.Room;
import io.my.habitat.microservice.room.RoomCRUDService;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * A verticle provides REST API for online room service.
 */
public class RestRoomAPIVerticle extends RestAPIVerticle {

  private static final String SERVICE_NAME = "shop-rest-api";

  private static final String API_SAVE = "/save";
  private static final String API_RETRIEVE = "/:creatorId";
  private static final String API_CLOSE = "/:creatorId";

  private final RoomCRUDService service;

  public RestRoomAPIVerticle(RoomCRUDService service) {
    this.service = service;
  }

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    Router router = Router.router(vertx);
    // body handler
    router.route().handler(BodyHandler.create());
    // API route handler
    router.post(API_SAVE).handler(this::apiSave);
    router.get(API_RETRIEVE).handler(this::apiRetrieve);
    router.delete(API_CLOSE).handler(this::apiClose);

    // get HTTP host and port from configuration, or use default value
    String host = config().getString("room.http.address", "0.0.0.0");
    int port = config().getInteger("room.http.port", 8085);

    createHttpServer(router, host, port)
      .compose(serverCreated -> publishHttpEndpoint(SERVICE_NAME, host, port))
      .setHandler(future.completer());
  }

  private void apiSave(RoutingContext context) {
    Room room = new Room(new JsonObject(context.getBodyAsString()));
    if (room.getCreatorId() == null) {
      badRequest(context, new IllegalStateException("creator id does not exist"));
    } else {
      JsonObject result = new JsonObject().put("message", "room_saved")
        .put("creatorId", room.getCreatorId());
      service.saveRoom(room, resultVoidHandler(context, result));
    }
  }

  private void apiRetrieve(RoutingContext context) {
    String creatorId = context.request().getParam("creatorId");
    service.retrieveRoom(creatorId, resultHandlerNonEmpty(context));
  }

  private void apiClose(RoutingContext context) {
    String creatorId = context.request().getParam("creatorId");
    service.removeRoom(creatorId, deleteResultHandler(context));
  }
}
