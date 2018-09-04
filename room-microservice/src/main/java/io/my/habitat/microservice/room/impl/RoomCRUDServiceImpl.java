package io.my.habitat.microservice.room.impl;

import io.my.habitat.microservice.room.Room;
import io.my.habitat.microservice.room.RoomCRUDService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * Implementation of {@link RoomCRUDService}. Use MongoDB as the persistence.
 */
public class RoomCRUDServiceImpl implements RoomCRUDService {

  private static final String COLLECTION = "room";

  private final MongoClient client;

  public RoomCRUDServiceImpl(Vertx vertx, JsonObject config) {
    this.client = MongoClient.createNonShared(vertx, config);
  }

  @Override
  public void saveRoom(Room room, Handler<AsyncResult<Void>> resultHandler) {
    client.save(COLLECTION, new JsonObject().put("_id", room.getCreatorId())
        .put("name", room.getName())
        .put("description", room.getDescription())
        .put("openTime", room.getOpenTime()),
      ar -> {
        if (ar.succeeded()) {
          resultHandler.handle(Future.succeededFuture());
        } else {
          resultHandler.handle(Future.failedFuture(ar.cause()));
        }
      }
    );
  }

  @Override
  public void retrieveRoom(String creatorId, Handler<AsyncResult<Room>> resultHandler) {
    JsonObject query = new JsonObject().put("_id", creatorId);
    client.findOne(COLLECTION, query, null, ar -> {
      if (ar.succeeded()) {
        if (ar.result() == null) {
          resultHandler.handle(Future.succeededFuture());
        } else {
          Room room = new Room(ar.result().put("creatorId", ar.result().getString("_id")));
          resultHandler.handle(Future.succeededFuture(room));
        }
      } else {
        resultHandler.handle(Future.failedFuture(ar.cause()));
      }
    });
  }

  @Override
  public void removeRoom(String creatorId, Handler<AsyncResult<Void>> resultHandler) {
    JsonObject query = new JsonObject().put("_id", creatorId);
    client.removeDocument(COLLECTION, query, ar -> {
      if (ar.succeeded()) {
        resultHandler.handle(Future.succeededFuture());
      } else {
        resultHandler.handle(Future.failedFuture(ar.cause()));
      }
    });
  }
}
