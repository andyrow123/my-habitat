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
!function (factory) {
  if (typeof require === 'function' && typeof module !== 'undefined') {
    factory();
  } else if (typeof define === 'function' && define.amd) {
    // AMD loader
    define('my-habitat-room-js/room_crud_service-proxy', [], factory);
  } else {
    // plain old include
    RoomCRUDService = factory();
  }
}(function () {

  /**
 A service interface for online room CURD operation.
 <p>
 This service is an event bus service (aka. service proxy).
 </p>

 @class
  */
  var RoomCRUDService = function(eb, address) {

    var j_eb = eb;
    var j_address = address;
    var closed = false;
    var that = this;
    var convCharCollection = function(coll) {
      var ret = [];
      for (var i = 0;i < coll.length;i++) {
        ret.push(String.fromCharCode(coll[i]));
      }
      return ret;
    };

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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"room":__args[0]}, {"action":"saveRoom"}, function(err, result) { __args[1](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"creatorId":__args[0]}, {"action":"retrieveRoom"}, function(err, result) { __args[1](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"creatorId":__args[0]}, {"action":"removeRoom"}, function(err, result) { __args[1](err, result &&result.body); });
        return;
      } else throw new TypeError('function invoked with invalid arguments');
    };

  };

  if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
      exports = module.exports = RoomCRUDService;
    } else {
      exports.RoomCRUDService = RoomCRUDService;
    }
  } else {
    return RoomCRUDService;
  }
});