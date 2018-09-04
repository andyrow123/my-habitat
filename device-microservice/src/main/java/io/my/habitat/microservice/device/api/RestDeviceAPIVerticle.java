package io.my.habitat.microservice.device.api;

import io.my.habitat.microservice.common.RestAPIVerticle;
import io.my.habitat.microservice.device.Device;
import io.my.habitat.microservice.device.DeviceService;
import io.vertx.core.Future;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


/**
 * This verticle exposes a HTTP endpoint to process shopping devices management with REST APIs.
 *
 * @author Eric Zhao
 */
public class RestDeviceAPIVerticle extends RestAPIVerticle {

  public static final String SERVICE_NAME = "device-rest-api";

  private static final String API_ADD = "/add";
  private static final String API_RETRIEVE_BY_PAGE = "/devices";
  private static final String API_RETRIEVE_ALL = "/devices";
  private static final String API_RETRIEVE_PRICE = "/:deviceId/price";
  private static final String API_RETRIEVE = "/:deviceId";
  private static final String API_UPDATE = "/:deviceId";
  private static final String API_DELETE = "/:deviceId";
  private static final String API_DELETE_ALL = "/all";

  private final DeviceService service;

  public RestDeviceAPIVerticle(DeviceService service) {
    this.service = service;
  }

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();
    final Router router = Router.router(vertx);
    // body handler
    router.route().handler(BodyHandler.create());
    // API route handler
    router.post(API_ADD).handler(this::apiAdd);
    router.get(API_RETRIEVE_BY_PAGE).handler(this::apiRetrieveByPage);
    router.get(API_RETRIEVE_ALL).handler(this::apiRetrieveAll);
    router.get(API_RETRIEVE_PRICE).handler(this::apiRetrievePrice);
    router.get(API_RETRIEVE).handler(this::apiRetrieve);
    router.patch(API_UPDATE).handler(this::apiUpdate);
    router.delete(API_DELETE).handler(this::apiDelete);
    router.delete(API_DELETE_ALL).handler(context -> requireLogin(context, this::apiDeleteAll));

    // get HTTP host and port from configuration, or use default value
    String host = config().getString("device.http.address", "0.0.0.0");
    int port = config().getInteger("device.http.port", 8082);

    // create HTTP server and publish REST service
    createHttpServer(router, host, port)
      .compose(serverCreated -> publishHttpEndpoint(SERVICE_NAME, host, port))
      .setHandler(future.completer());
  }

  private void apiAdd(RoutingContext context) {
    try {
      Device device = new Device(new JsonObject(context.getBodyAsString()));
      service.addDevice(device, resultHandler(context, r -> {
        String result = new JsonObject().put("message", "device_added")
          .put("deviceId", device.getDeviceId())
          .encodePrettily();
        context.response().setStatusCode(201)
          .putHeader("content-type", "application/json")
          .end(result);
      }));
    } catch (DecodeException e) {
      badRequest(context, e);
    }
  }

  private void apiRetrieve(RoutingContext context) {
    String deviceId = context.request().getParam("deviceId");
    service.retrieveDevice(deviceId, resultHandlerNonEmpty(context));
  }

  private void apiRetrievePrice(RoutingContext context) {
    String deviceId = context.request().getParam("deviceId");
    service.retrieveDevicePrice(deviceId, resultHandlerNonEmpty(context));
  }

  private void apiRetrieveByPage(RoutingContext context) {
    try {
      String p = context.request().getParam("p");
      int page = p == null ? 1 : Integer.parseInt(p);
      service.retrieveDevicesByPage(page, resultHandler(context, Json::encodePrettily));
    } catch (Exception ex) {
      badRequest(context, ex);
    }
  }

  private void apiRetrieveAll(RoutingContext context) {
    service.retrieveAllDevices(resultHandler(context, Json::encodePrettily));
  }

  private void apiUpdate(RoutingContext context) {
    notImplemented(context);
  }

  private void apiDelete(RoutingContext context) {
    String deviceId = context.request().getParam("deviceId");
    service.deleteDevice(deviceId, deleteResultHandler(context));
  }

  private void apiDeleteAll(RoutingContext context, JsonObject principle) {
    service.deleteAllDevices(deleteResultHandler(context));
  }

}
