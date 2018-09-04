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

/** @module my-habitat-room-js/room_crud_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JRoomCRUDService = Java.type('io.my.habitat.microservice.room.RoomCRUDService');
var Room = Java.type('io.my.habitat.microservice.room.Room');

/**
 A service interface for online room CURD operation.
 <p>
 This service is an event bus service (aka. service proxy).
 </p>

 @class
*/
var RoomCRUDService = function(j_val) {

  var j_roomCRUDService = j_val;
  var that = this;

  /**
   Save an online room to the persistence layer. This is a so called `upsert` operation.
   This is used to update room info, or just apply for a new room.

   @public
   @param room {Object} room object 
   @param resultHandler {function} async result handler 
   */
  this.saveRoom = function(room, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_roomCRUDService["saveRoom(io.my.habitat.microservice.room.Room,io.vertx.core.Handler)"](room != null ? new Room(new JsonObject(Java.asJSONCompatible(room))) : null, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve an online room by creator id.

   @public
   @param creatorId {string} creator id, refers to an independent online room 
   @param resultHandler {function} async result handler 
   */
  this.retrieveRoom = function(creatorId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_roomCRUDService["retrieveRoom(java.lang.String,io.vertx.core.Handler)"](creatorId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Remove an online room whose creator is <code>creatorId</code>.
   This is used to close an online room.

   @public
   @param creatorId {string} creator id, refers to an independent online room 
   @param resultHandler {function} async result handler 
   */
  this.removeRoom = function(creatorId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_roomCRUDService["removeRoom(java.lang.String,io.vertx.core.Handler)"](creatorId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_roomCRUDService;
};

RoomCRUDService._jclass = utils.getJavaClass("io.my.habitat.microservice.room.RoomCRUDService");
RoomCRUDService._jtype = {
  accept: function(obj) {
    return RoomCRUDService._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(RoomCRUDService.prototype, {});
    RoomCRUDService.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
RoomCRUDService._create = function(jdel) {
  var obj = Object.create(RoomCRUDService.prototype, {});
  RoomCRUDService.apply(obj, arguments);
  return obj;
}
module.exports = RoomCRUDService;