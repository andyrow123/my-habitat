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

package io.my.habitat.microservice.device;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.my.habitat.microservice.device.Device}.
 *
 * NOTE: This class has been automatically generated from the {@link io.my.habitat.microservice.device.Device} original class using Vert.x codegen.
 */
public class DeviceConverter {

  public static void fromJson(JsonObject json, Device obj) {
    if (json.getValue("creatorId") instanceof String) {
      obj.setCreatorId((String)json.getValue("creatorId"));
    }
    if (json.getValue("deviceId") instanceof String) {
      obj.setDeviceId((String)json.getValue("deviceId"));
    }
    if (json.getValue("illustration") instanceof String) {
      obj.setIllustration((String)json.getValue("illustration"));
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("price") instanceof Number) {
      obj.setPrice(((Number)json.getValue("price")).doubleValue());
    }
    if (json.getValue("type") instanceof String) {
      obj.setType((String)json.getValue("type"));
    }
  }

  public static void toJson(Device obj, JsonObject json) {
    if (obj.getCreatorId() != null) {
      json.put("creatorId", obj.getCreatorId());
    }
    if (obj.getDeviceId() != null) {
      json.put("deviceId", obj.getDeviceId());
    }
    if (obj.getIllustration() != null) {
      json.put("illustration", obj.getIllustration());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    json.put("price", obj.getPrice());
    if (obj.getType() != null) {
      json.put("type", obj.getType());
    }
  }
}