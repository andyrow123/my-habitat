package io.my.habitat.microservice.device;

import io.my.habitat.microservice.common.BaseMicroserviceVerticle;
import io.my.habitat.microservice.common.service.ExampleHelper;
import io.my.habitat.microservice.device.api.RestDeviceAPIVerticle;
import io.my.habitat.microservice.device.impl.DeviceServiceImpl;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;


/**
 * A verticle publishing the product service.
 *
 * @author Eric Zhao
 */
public class DeviceVerticle extends BaseMicroserviceVerticle {

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    // create the service instance
    DeviceService deviceService = new DeviceServiceImpl(vertx, config());
    // register the service proxy on event bus
    ProxyHelper.registerService(DeviceService.class, vertx, deviceService, DeviceService.SERVICE_ADDRESS);
    // publish the service in the discovery infrastructure
    initProductDatabase(deviceService)
      .compose(databaseOkay -> publishEventBusService(DeviceService.SERVICE_NAME, DeviceService.SERVICE_ADDRESS, DeviceService.class))
      .compose(servicePublished -> deployRestService(deviceService))
      .setHandler(future.completer());
  }

  private Future<Void> initProductDatabase(DeviceService service) {
    Future<Void> initFuture = Future.future();
    service.initializePersistence(initFuture.completer());
    return initFuture.map(v -> {
      ExampleHelper.initData(vertx, config());
      return null;
    });
  }

  private Future<Void> deployRestService(DeviceService service) {
    Future<String> future = Future.future();
    vertx.deployVerticle(new RestDeviceAPIVerticle(service),
      new DeploymentOptions().setConfig(config()),
      future.completer());
    return future.map(r -> null);
  }

}
