/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.my.habitat.microservice.room.rxjava;

import io.my.habitat.microservice.room.Room;
import io.my.habitat.microservice.room.RoomCRUDService;
import rx.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * A service interface for online room CURD operation.
 * <p>
 * This service is an event bus service (aka. service proxy).
 * </p>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link RoomCRUDService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.rxjava.RxGen(RoomCRUDService.class)
public class RoomCRUDService {

  public static final io.vertx.lang.rxjava.TypeArg<RoomCRUDService> __TYPE_ARG = new io.vertx.lang.rxjava.TypeArg<>(
    obj -> new RoomCRUDService((RoomCRUDService) obj),
    RoomCRUDService::getDelegate
  );

  private final RoomCRUDService delegate;
  
  public RoomCRUDService(RoomCRUDService delegate) {
    this.delegate = delegate;
  }

  public RoomCRUDService getDelegate() {
    return delegate;
  }

  /**
   * Save an online room to the persistence layer. This is a so called `upsert` operation.
   * This is used to update room info, or just apply for a new room.
   * @param room room object
   * @param resultHandler async result handler
   */
  public void saveRoom(Room room, Handler<AsyncResult<Void>> resultHandler) {
    delegate.saveRoom(room, resultHandler);
  }

  /**
   * Save an online room to the persistence layer. This is a so called `upsert` operation.
   * This is used to update room info, or just apply for a new room.
   * @param room room object
   * @return 
   */
  public Single<Void> rxSaveRoom(Room room) {
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      saveRoom(room, fut);
    }));
  }

  /**
   * Retrieve an online room by creator id.
   * @param creatorId creator id, refers to an independent online room
   * @param resultHandler async result handler
   */
  public void retrieveRoom(String creatorId, Handler<AsyncResult<Room>> resultHandler) {
    delegate.retrieveRoom(creatorId, resultHandler);
  }

  /**
   * Retrieve an online room by creator id.
   * @param creatorId creator id, refers to an independent online room
   * @return 
   */
  public Single<Room> rxRetrieveRoom(String creatorId) {
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      retrieveRoom(creatorId, fut);
    }));
  }

  /**
   * Remove an online room whose creator is <code>creatorId</code>.
   * This is used to close an online room.
   * @param creatorId creator id, refers to an independent online room
   * @param resultHandler async result handler
   */
  public void removeRoom(String creatorId, Handler<AsyncResult<Void>> resultHandler) {
    delegate.removeRoom(creatorId, resultHandler);
  }

  /**
   * Remove an online room whose creator is <code>creatorId</code>.
   * This is used to close an online room.
   * @param creatorId creator id, refers to an independent online room
   * @return 
   */
  public Single<Void> rxRemoveRoom(String creatorId) {
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      removeRoom(creatorId, fut);
    }));
  }


  public static RoomCRUDService newInstance(RoomCRUDService arg) {
    return arg != null ? new RoomCRUDService(arg) : null;
  }
}
