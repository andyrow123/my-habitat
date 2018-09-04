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

package io.my.habitat.microservice.device.rxjava;

import java.util.Map;
import rx.Observable;
import rx.Single;
import java.util.List;
import io.my.habitat.microservice.device.Device;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * A service interface managing devices.
 * <p>
 * This service is an event bus service (aka. service proxy)
 * </p>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link io.my.habitat.microservice.device.DeviceService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.rxjava.RxGen(io.my.habitat.microservice.device.DeviceService.class)
public class DeviceService {

  public static final io.vertx.lang.rxjava.TypeArg<DeviceService> __TYPE_ARG = new io.vertx.lang.rxjava.TypeArg<>(
    obj -> new DeviceService((io.my.habitat.microservice.device.DeviceService) obj),
    DeviceService::getDelegate
  );

  private final io.my.habitat.microservice.device.DeviceService delegate;
  
  public DeviceService(io.my.habitat.microservice.device.DeviceService delegate) {
    this.delegate = delegate;
  }

  public io.my.habitat.microservice.device.DeviceService getDelegate() {
    return delegate;
  }

  /**
   * Initialize the persistence.
   * @param resultHandler the result handler will be called as soon as the initialization has been accomplished. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService initializePersistence(Handler<AsyncResult<Void>> resultHandler) { 
    delegate.initializePersistence(resultHandler);
    return this;
  }

  /**
   * Initialize the persistence.
   * @return 
   */
  public Single<Void> rxInitializePersistence() { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      initializePersistence(fut);
    }));
  }

  /**
   * Add a device to the persistence.
   * @param device a device entity that we want to add
   * @param resultHandler the result handler will be called as soon as the device has been added. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService addDevice(Device device, Handler<AsyncResult<Void>> resultHandler) { 
    delegate.addDevice(device, resultHandler);
    return this;
  }

  /**
   * Add a device to the persistence.
   * @param device a device entity that we want to add
   * @return 
   */
  public Single<Void> rxAddDevice(Device device) { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      addDevice(device, fut);
    }));
  }

  /**
   * Retrieve the device with certain `deviceId`.
   * @param deviceId device id
   * @param resultHandler the result handler will be called as soon as the device has been retrieved. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService retrieveDevice(String deviceId, Handler<AsyncResult<Device>> resultHandler) { 
    delegate.retrieveDevice(deviceId, resultHandler);
    return this;
  }

  /**
   * Retrieve the device with certain `deviceId`.
   * @param deviceId device id
   * @return 
   */
  public Single<Device> rxRetrieveDevice(String deviceId) { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      retrieveDevice(deviceId, fut);
    }));
  }

  /**
   * Retrieve the device price with certain `deviceId`.
   * @param deviceId device id
   * @param resultHandler the result handler will be called as soon as the device has been retrieved. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService retrieveDevicePrice(String deviceId, Handler<AsyncResult<JsonObject>> resultHandler) { 
    delegate.retrieveDevicePrice(deviceId, resultHandler);
    return this;
  }

  /**
   * Retrieve the device price with certain `deviceId`.
   * @param deviceId device id
   * @return 
   */
  public Single<JsonObject> rxRetrieveDevicePrice(String deviceId) { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      retrieveDevicePrice(deviceId, fut);
    }));
  }

  /**
   * Retrieve all devices.
   * @param resultHandler the result handler will be called as soon as the devices have been retrieved. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService retrieveAllDevices(Handler<AsyncResult<List<Device>>> resultHandler) { 
    delegate.retrieveAllDevices(resultHandler);
    return this;
  }

  /**
   * Retrieve all devices.
   * @return 
   */
  public Single<List<Device>> rxRetrieveAllDevices() { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      retrieveAllDevices(fut);
    }));
  }

  /**
   * Retrieve devices by page.
   * @param page 
   * @param resultHandler the result handler will be called as soon as the devices have been retrieved. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService retrieveDevicesByPage(int page, Handler<AsyncResult<List<Device>>> resultHandler) { 
    delegate.retrieveDevicesByPage(page, resultHandler);
    return this;
  }

  /**
   * Retrieve devices by page.
   * @param page 
   * @return 
   */
  public Single<List<Device>> rxRetrieveDevicesByPage(int page) { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      retrieveDevicesByPage(page, fut);
    }));
  }

  /**
   * Delete a device from the persistence
   * @param deviceId device id
   * @param resultHandler the result handler will be called as soon as the device has been removed. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService deleteDevice(String deviceId, Handler<AsyncResult<Void>> resultHandler) { 
    delegate.deleteDevice(deviceId, resultHandler);
    return this;
  }

  /**
   * Delete a device from the persistence
   * @param deviceId device id
   * @return 
   */
  public Single<Void> rxDeleteDevice(String deviceId) { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      deleteDevice(deviceId, fut);
    }));
  }

  /**
   * Delete all devices from the persistence
   * @param resultHandler the result handler will be called as soon as the devices have been removed. The async result indicates whether the operation was successful or not.
   * @return 
   */
  public DeviceService deleteAllDevices(Handler<AsyncResult<Void>> resultHandler) { 
    delegate.deleteAllDevices(resultHandler);
    return this;
  }

  /**
   * Delete all devices from the persistence
   * @return 
   */
  public Single<Void> rxDeleteAllDevices() { 
    return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> {
      deleteAllDevices(fut);
    }));
  }


  public static DeviceService newInstance(io.my.habitat.microservice.device.DeviceService arg) {
    return arg != null ? new DeviceService(arg) : null;
  }
}
