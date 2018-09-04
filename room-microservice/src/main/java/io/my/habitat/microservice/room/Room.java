package io.my.habitat.microservice.room;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Online room data object.
 *
 * @author Eric Zhao
 */
@DataObject(generateConverter = true)
public class Room {

  private String creatorId;
  private String name;
  private String description;
  private Long openTime;

  public Room() {
    this.openTime = System.currentTimeMillis();
  }

  public Room(Room other) {
    this.openTime = other.openTime;
    this.description = other.description;
    this.name = other.name;
    this.creatorId = other.creatorId;
  }

  public Room(JsonObject json) {
    RoomConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    RoomConverter.toJson(this, json);
    return json;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public Room setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Room setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Room setDescription(String description) {
    this.description = description;
    return this;
  }

  public Long getOpenTime() {
    return openTime;
  }

  public Room setOpenTime(Long openTime) {
    this.openTime = openTime;
    return this;
  }

  @Override
  public String toString() {
    return this.toJson().encodePrettily();
  }
}
