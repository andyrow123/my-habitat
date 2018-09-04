package io.my.habitat.microservice.room;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * A service interface for online room CURD operation.
 * <p>
 * This service is an event bus service (aka. service proxy).
 * </p>
 *
 * @author Eric Zhao
 */
@VertxGen
@ProxyGen
public interface RoomCRUDService {

  String SERVICE_NAME = "room-eb-service";

  String SERVICE_ADDRESS = "service.room";

  /**
   * Save an online room to the persistence layer. This is a so called `upsert` operation.
   * This is used to update room info, or just apply for a new room.
   *
   * @param room         room object
   * @param resultHandler async result handler
   */
  void saveRoom(Room room, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Retrieve an online room by creator id.
   *
   * @param creatorId creator id, refers to an independent online room
   * @param resultHandler async result handler
   */
  void retrieveRoom(String creatorId, Handler<AsyncResult<Room>> resultHandler);

  /**
   * Remove an online room whose creator is {@code creatorId}.
   * This is used to close an online room.
   *
   * @param creatorId creator id, refers to an independent online room
   * @param resultHandler async result handler
   */
  void removeRoom(String creatorId, Handler<AsyncResult<Void>> resultHandler);

}
