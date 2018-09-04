package io.my.habitat.microservice.device;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * A device tuple represents the amount of a certain device in a shopping.
 */
@DataObject(generateConverter = true)
public class DeviceTuple /*extends Tuple4<String, String, Double, Integer>*/ {

  private String deviceId;
  private String creatorId;
  private Double price;
  private Integer amount;

  public DeviceTuple() {
    // empty constructor
  }

  public DeviceTuple(String deviceId, String creatorId, Double price, Integer amount) {
    this.deviceId = deviceId;
    this.creatorId = creatorId;
    this.price = price;
    this.amount = amount;
  }

  public DeviceTuple(Device device, Integer amount) {
    this.deviceId = device.getDeviceId();
    this.creatorId = device.getCreatorId();
    this.price = device.getPrice();
    this.amount = amount;
  }

  public DeviceTuple(JsonObject json) {
    DeviceTupleConverter.fromJson(json, this);
  }

  public DeviceTuple(DeviceTuple other) {
    this.deviceId = other.deviceId;
    this.creatorId = other.creatorId;
    this.price = other.price;
    this.amount = other.amount;
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    DeviceTupleConverter.toJson(this, json);
    return json;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public DeviceTuple setDeviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public DeviceTuple setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public DeviceTuple setPrice(Double price) {
    this.price = price;
    return this;
  }

  public Integer getAmount() {
    return amount;
  }

  public DeviceTuple setAmount(Integer amount) {
    this.amount = amount;
    return this;
  }

  @Override
  public String toString() {
    return "(" + deviceId + "," + creatorId + "," + amount + ")";
  }
}
