package io.my.habitat.microservice.device.impl;

import io.my.habitat.microservice.common.service.JdbcRepositoryWrapper;
import io.my.habitat.microservice.device.Device;
import io.my.habitat.microservice.device.DeviceService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JDBC implementation of {@link DeviceService}.
 *
 * @author Eric Zhao
 */
public class DeviceServiceImpl extends JdbcRepositoryWrapper implements DeviceService {

  private static final int PAGE_LIMIT = 10;

  public DeviceServiceImpl(Vertx vertx, JsonObject config) {
    super(vertx, config);
  }

  @Override
  public DeviceService initializePersistence(Handler<AsyncResult<Void>> resultHandler) {
    client.getConnection(connHandler(resultHandler, connection -> {
      connection.execute(CREATE_STATEMENT, r -> {
        resultHandler.handle(r);
        connection.close();
      });
    }));
    return this;
  }

  @Override
  public DeviceService addDevice(Device device, Handler<AsyncResult<Void>> resultHandler) {
    JsonArray params = new JsonArray()
      .add(device.getDeviceId())
      .add(device.getCreatorId())
      .add(device.getName())
      .add(device.getPrice())
      .add(device.getIllustration())
      .add(device.getType());
    executeNoResult(params, INSERT_STATEMENT, resultHandler);
    return this;
  }

  @Override
  public DeviceService retrieveDevice(String deviceId, Handler<AsyncResult<Device>> resultHandler) {
    this.retrieveOne(deviceId, FETCH_STATEMENT)
      .map(option -> option.map(Device::new).orElse(null))
      .setHandler(resultHandler);
    return this;
  }

  @Override
  public DeviceService retrieveDevicePrice(String deviceId, Handler<AsyncResult<JsonObject>> resultHandler) {
    this.retrieveOne(deviceId, "SELECT price FROM device WHERE deviceId = ?")
      .map(option -> option.orElse(null))
      .setHandler(resultHandler);
    return this;
  }

  @Override
  public DeviceService retrieveDevicesByPage(int page, Handler<AsyncResult<List<Device>>> resultHandler) {
    this.retrieveByPage(page, PAGE_LIMIT, FETCH_WITH_PAGE_STATEMENT)
      .map(rawList -> rawList.stream()
        .map(Device::new)
        .collect(Collectors.toList())
      )
      .setHandler(resultHandler);
    return this;
  }

  @Override
  public DeviceService retrieveAllDevices(Handler<AsyncResult<List<Device>>> resultHandler) {
    this.retrieveAll(FETCH_ALL_STATEMENT)
      .map(rawList -> rawList.stream()
        .map(Device::new)
        .collect(Collectors.toList())
      )
      .setHandler(resultHandler);
    return this;
  }

  @Override
  public DeviceService deleteDevice(String deviceId, Handler<AsyncResult<Void>> resultHandler) {
    this.removeOne(deviceId, DELETE_STATEMENT, resultHandler);
    return this;
  }

  @Override
  public DeviceService deleteAllDevices(Handler<AsyncResult<Void>> resultHandler) {
    this.removeAll(DELETE_ALL_STATEMENT, resultHandler);
    return this;
  }

  // SQL statements

  private static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS `device` (\n" +
    "  `deviceId` VARCHAR(60) NOT NULL,\n" +
    "  `creatorId` varchar(30) NOT NULL,\n" +
    "  `name` varchar(255) NOT NULL,\n" +
    "  `price` double NOT NULL,\n" +
    "  `illustration` MEDIUMTEXT NOT NULL,\n" +
    "  `type` varchar(45) NOT NULL,\n" +
    "  PRIMARY KEY (`deviceId`),\n" +
    "  KEY `index_creator` (`creatorId`) )";
  private static final String INSERT_STATEMENT = "INSERT INTO device (`deviceId`, `creatorId`, `name`, `price`, `illustration`, `type`) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String FETCH_STATEMENT = "SELECT * FROM device WHERE deviceId = ?";
  private static final String FETCH_ALL_STATEMENT = "SELECT * FROM device";
  private static final String FETCH_WITH_PAGE_STATEMENT = "SELECT * FROM device LIMIT ?, ?";
  private static final String DELETE_STATEMENT = "DELETE FROM device WHERE deviceId = ?";
  private static final String DELETE_ALL_STATEMENT = "DELETE FROM device";
}
