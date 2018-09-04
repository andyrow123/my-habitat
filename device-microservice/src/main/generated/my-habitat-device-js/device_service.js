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

/** @module my-habitat-device-js/device_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JDeviceService = Java.type('io.my.habitat.microservice.device.DeviceService');
var Device = Java.type('io.my.habitat.microservice.device.Device');

/**
 A service interface managing devices.
 <p>
 This service is an event bus service (aka. service proxy)
 </p>

 @class
*/
var DeviceService = function(j_val) {

  var j_deviceService = j_val;
  var that = this;

  /**
   Initialize the persistence.

   @public
   @param resultHandler {function} the result handler will be called as soon as the initialization has been accomplished. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.initializePersistence = function(resultHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_deviceService["initializePersistence(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Add a device to the persistence.

   @public
   @param device {Object} a device entity that we want to add 
   @param resultHandler {function} the result handler will be called as soon as the device has been added. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.addDevice = function(device, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_deviceService["addDevice(io.my.habitat.microservice.device.Device,io.vertx.core.Handler)"](device != null ? new Device(new JsonObject(Java.asJSONCompatible(device))) : null, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve the device with certain `deviceId`.

   @public
   @param deviceId {string} device id 
   @param resultHandler {function} the result handler will be called as soon as the device has been retrieved. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.retrieveDevice = function(deviceId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_deviceService["retrieveDevice(java.lang.String,io.vertx.core.Handler)"](deviceId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve the device price with certain `deviceId`.

   @public
   @param deviceId {string} device id 
   @param resultHandler {function} the result handler will be called as soon as the device has been retrieved. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.retrieveDevicePrice = function(deviceId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_deviceService["retrieveDevicePrice(java.lang.String,io.vertx.core.Handler)"](deviceId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve all devices.

   @public
   @param resultHandler {function} the result handler will be called as soon as the devices have been retrieved. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.retrieveAllDevices = function(resultHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_deviceService["retrieveAllDevices(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve devices by page.

   @public
   @param page {number} 
   @param resultHandler {function} the result handler will be called as soon as the devices have been retrieved. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.retrieveDevicesByPage = function(page, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'function') {
      j_deviceService["retrieveDevicesByPage(int,io.vertx.core.Handler)"](page, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Delete a device from the persistence

   @public
   @param deviceId {string} device id 
   @param resultHandler {function} the result handler will be called as soon as the device has been removed. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.deleteDevice = function(deviceId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_deviceService["deleteDevice(java.lang.String,io.vertx.core.Handler)"](deviceId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Delete all devices from the persistence

   @public
   @param resultHandler {function} the result handler will be called as soon as the devices have been removed. The async result indicates whether the operation was successful or not. 
   @return {DeviceService}
   */
  this.deleteAllDevices = function(resultHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_deviceService["deleteAllDevices(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_deviceService;
};

DeviceService._jclass = utils.getJavaClass("io.my.habitat.microservice.device.DeviceService");
DeviceService._jtype = {
  accept: function(obj) {
    return DeviceService._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(DeviceService.prototype, {});
    DeviceService.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
DeviceService._create = function(jdel) {
  var obj = Object.create(DeviceService.prototype, {});
  DeviceService.apply(obj, arguments);
  return obj;
}
module.exports = DeviceService;