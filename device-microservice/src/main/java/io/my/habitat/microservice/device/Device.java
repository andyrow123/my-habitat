package io.my.habitat.microservice.device;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Device data object.
 *
 * @author Eric Zhao
 */
@DataObject(generateConverter = true)
public class Device {

  private String deviceId;
  private String creatorId;
  private String name;
  private double price = 0.0d;
  private String illustration;
  private String type;

  public Device() {
    // Empty constructor
  }

  public Device(Device other) {
    this.deviceId = other.deviceId;
    this.creatorId = other.creatorId;
    this.name = other.name;
    this.price = other.price;
    this.illustration = other.illustration;
    this.type = other.type;
  }

  public Device(JsonObject json) {
    DeviceConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    DeviceConverter.toJson(this, json);
    return json;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public Device setDeviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public Device setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Device setName(String name) {
    this.name = name;
    return this;
  }

  public double getPrice() {
    return price;
  }

  public Device setPrice(double price) {
    this.price = price;
    return this;
  }

  public String getIllustration() {
    return illustration;
  }

  public Device setIllustration(String illustration) {
    this.illustration = illustration;
    return this;
  }

  public String getType() {
    return type;
  }

  public Device setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Device device = (Device) o;

    return deviceId.equals(device.deviceId) && creatorId.equals(device.creatorId);
  }

  @Override
  public int hashCode() {
    int result = deviceId.hashCode();
    result = 31 * result + creatorId.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return this.toJson().encodePrettily();
  }
}
