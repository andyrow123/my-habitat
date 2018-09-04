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
!function (factory) {
  if (typeof require === 'function' && typeof module !== 'undefined') {
    factory();
  } else if (typeof define === 'function' && define.amd) {
    // AMD loader
    define('my-habitat-device-js/device_service-proxy', [], factory);
  } else {
    // plain old include
    DeviceService = factory();
  }
}(function () {

  /**
 A service interface managing devices.
 <p>
 This service is an event bus service (aka. service proxy)
 </p>

 @class
  */
  var DeviceService = function(eb, address) {

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
     Initialize the persistence.

     @public
     @param resultHandler {function} the result handler will be called as soon as the initialization has been accomplished. The async result indicates whether the operation was successful or not. 
     @return {DeviceService}
     */
    this.initializePersistence = function(resultHandler) {
      var __args = arguments;
      if (__args.length === 1 && typeof __args[0] === 'function') {
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {}, {"action":"initializePersistence"}, function(err, result) { __args[0](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"device":__args[0]}, {"action":"addDevice"}, function(err, result) { __args[1](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"deviceId":__args[0]}, {"action":"retrieveDevice"}, function(err, result) { __args[1](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"deviceId":__args[0]}, {"action":"retrieveDevicePrice"}, function(err, result) { __args[1](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {}, {"action":"retrieveAllDevices"}, function(err, result) { __args[0](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"page":__args[0]}, {"action":"retrieveDevicesByPage"}, function(err, result) { __args[1](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"deviceId":__args[0]}, {"action":"deleteDevice"}, function(err, result) { __args[1](err, result &&result.body); });
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {}, {"action":"deleteAllDevices"}, function(err, result) { __args[0](err, result &&result.body); });
        return that;
      } else throw new TypeError('function invoked with invalid arguments');
    };

  };

  if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
      exports = module.exports = DeviceService;
    } else {
      exports.DeviceService = DeviceService;
    }
  } else {
    return DeviceService;
  }
});