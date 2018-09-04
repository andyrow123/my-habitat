package io.my.habitat.microservice.device;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * A service interface managing devices.
 * <p>
 * This service is an event bus service (aka. service proxy)
 * </p>
 *
 * @author Eric Zhao
 */
@VertxGen
@ProxyGen
public interface DeviceService {

  /**
   * The name of the event bus service.
   */
  String SERVICE_NAME = "device-eb-service";

  /**
   * The address on which the service is published.
   */
  String SERVICE_ADDRESS = "service.device";

  /**
   * A static method that creates a device service.
   *
   * @param config a json object for configuration
   * @return initialized device service
   */
  // static DeviceService createService(Vertx vertx, JsonObject config)

  /**
   * Initialize the persistence.
   *
   * @param resultHandler the result handler will be called as soon as the initialization has been accomplished. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService initializePersistence(Handler<AsyncResult<Void>> resultHandler);

  /**
   * Add a device to the persistence.
   *
   * @param device       a device entity that we want to add
   * @param resultHandler the result handler will be called as soon as the device has been added. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService addDevice(Device device, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Retrieve the device with certain `deviceId`.
   *
   * @param deviceId     device id
   * @param resultHandler the result handler will be called as soon as the device has been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService retrieveDevice(String deviceId, Handler<AsyncResult<Device>> resultHandler);

  /**
   * Retrieve the device price with certain `deviceId`.
   *
   * @param deviceId     device id
   * @param resultHandler the result handler will be called as soon as the device has been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService retrieveDevicePrice(String deviceId, Handler<AsyncResult<JsonObject>> resultHandler);

  /**
   * Retrieve all devices.
   *
   * @param resultHandler the result handler will be called as soon as the devices have been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService retrieveAllDevices(Handler<AsyncResult<List<Device>>> resultHandler);

  /**
   * Retrieve devices by page.
   *
   * @param resultHandler the result handler will be called as soon as the devices have been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService retrieveDevicesByPage(int page, Handler<AsyncResult<List<Device>>> resultHandler);

  /**
   * Delete a device from the persistence
   *
   * @param deviceId     device id
   * @param resultHandler the result handler will be called as soon as the device has been removed. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService deleteDevice(String deviceId, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Delete all devices from the persistence
   *
   * @param resultHandler the result handler will be called as soon as the devices have been removed. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  DeviceService deleteAllDevices(Handler<AsyncResult<Void>> resultHandler);

}
