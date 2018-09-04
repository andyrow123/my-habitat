'use strict';

var appControllers = angular.module('appControllers', []);

const API_URI = '/api';

vertxApp.controller('AppIndexCtrl', ['$scope', '$http', '$templateCache', '$routeParams',
  function ($scope, $http, $templateCache, $routeParams) {
    $scope.devices = [];
    $scope.reqUrl = "/api/device/devices";
    if ($routeParams.p) {
      $scope.reqUrl = '/api/device/devices?p=' + $routeParams.p;
    } else {
      $scope.reqUrl = '/api/device/devices';
    }
    let getDevices = () => {
      $http({
        method: 'GET',
        url: $scope.reqUrl
      }).success((data, status, headers, config) => {
        $scope.devices = data;
      });
    };

    getDevices();
  }]);

vertxApp.controller('DeviceDetailCtrl', ['$scope', '$routeParams', '$http', '$location',
  function ($scope, $routeParams, $http, $location) {
    $scope.deviceDetailUrl = API_URI + '/device/' + $routeParams.deviceId;
    $scope.inventoryUrl = API_URI + '/inventory/' + $routeParams.deviceId;
    $scope.device = {};
    $scope.inventory = false;

    $scope.$on('logout', (event, msg) => {
      fetchDeviceDetail();
    });

    var fetchDeviceDetail = () => {
      $http({
        method: 'GET',
        url: $scope.deviceDetailUrl
      }).success((data, status, headers, config) => {
        $scope.device = data;
        $scope.device.poster_image = '/assets/img/posters/' + $scope.device.deviceId + '.jpg';
      }).error((data, status, headers, config) => {
        if (status == 404) {
          $location.path("/404");
          $location.reload($location.path);
        }
      });
      $http({
        method: 'GET',
        url: $scope.inventoryUrl
      }).success((data, status, headers, config) => {
        $scope.inventory = data > 0;
      })
    };

    fetchDeviceDetail();
  }]);

vertxApp.controller('CartCtrl', ['$scope', '$http', '$templateCache',
  function ($scope, $http) {
    $scope.cartUrl = '/api/cart/cart';
    $scope.cart = {};

    $scope.$on('cartEvents', (event, msg) => {
      fetchCart();
    });

    var fetchCart = function () {
      $http({
        method: 'GET',
        url: $scope.cartUrl
      }).success(data => {
        $scope.cart = data;
        $scope.cart.total = 0;
        $scope.cart.totalItems = 0;
        for (var i = 0; i < $scope.cart.deviceItems.length; i++) {
          $scope.cart.deviceItems[i].posterImage = '/assets/img/posters/' + $scope.cart.deviceItems[i].deviceId + '.jpg';
          $scope.cart.deviceItems[i].originalQuantity = $scope.cart.deviceItems[i].amount;
          $scope.cart.total += $scope.cart.deviceItems[i].amount * $scope.cart.deviceItems[i].price;
          $scope.cart.totalItems += $scope.cart.deviceItems[i].amount;
        }
      }).error(() => {

      });
    };

    fetchCart();
  }]);

vertxApp.controller('AddToCartCtrl', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope) {
  $scope.amount = 0;
  $scope.deviceId = "";
  $scope.addToCart = () => {
    if ($scope.amount && $scope.amount > 0) {
      var req = {
        method: 'POST',
        url: '/api/cart/events',
        headers: {
          'Content-Type': "application/json"
        },
        data: {
          "cartEventType": "ADD_ITEM",
          "deviceId": $scope.device.deviceId,
          "amount": $scope.amount,
          "userId": $rootScope.user.id
        }
      };

      $http(req).then(() => {
        $scope.amount = 0;
        function showAlert() {
          $("#addDeviceAlert").addClass("in");
        }

        function hideAlert() {
          $("#addDeviceAlert").removeClass("in");
        }

        window.setTimeout(function () {
          showAlert();
          window.setTimeout(function () {
            hideAlert();
          }, 2000);
        }, 20);
      });
    }
  };
}]);

vertxApp.controller('UpdateCartCtrl', ['$rootScope', '$scope', '$http', function ($rootScope, $scope, $http) {
  $scope.deviceId = "";

  $scope.updateCart = () => {
    let delta = 0;
    if ($scope.item.amount >= 0 && $scope.item.originalQuantity > 0 &&
      $scope.item.amount != $scope.item.originalQuantity) {
      var updateCount = $scope.item.amount - $scope.item.originalQuantity;
      delta = Math.abs(updateCount);
      if (delta > 0) {
        var req = {
          method: 'POST',
          url: '/api/cart/events',
          headers: {
            'Content-Type': "application/json"
          },
          data: {
            "cartEventType": updateCount <= 0 ? "REMOVE_ITEM" : "ADD_ITEM",
            "deviceId": $scope.item.deviceId,
            "amount": delta,
            "userId": $rootScope.user.id
          }
        };

        var selector = "#updateDeviceAlert." + $scope.item.deviceId;

        $http(req).then(() => {

          if (updateCount <= 0) {
            $rootScope.$broadcast('cartEvents', "update");
          }

          $scope.item.originalQuantity = $scope.item.amount;

          function showAlert() {
            $(selector).find("p").text("Cart updated");
            $(selector).removeClass("alert-error")
              .addClass("alert-success")
              .addClass("in");
          }

          function hideAlert() {
            $(selector).removeClass("in");
          }

          window.setTimeout(function () {
            showAlert();
            window.setTimeout(function () {
              hideAlert();
            }, 2000);
          }, 20);
        });
      }
    } else if (delta < 0) {
      $rootScope.item.amount = $scope.item.originalQuantity;
      if ($scope.item.amount <= 0) {
        $scope.$broadcast('cartEvents', "update");
      }
      window.setTimeout(() => {
        $(selector).find("p").text("Invalid quantity");
        $(selector).removeClass("alert-success")
          .addClass("alert-error")
          .addClass("in");
        window.setTimeout(() => {
          $(selector).removeClass("in");
        }, 2000);
      }, 20);
    }
  };
}]);

vertxApp.controller('CheckoutCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
  $scope.checkout = () => {
    var req = {
      method: 'POST',
      url: '/api/cart/checkout',
      headers: {
        'Content-Type': "application/json"
      },
      data: {}
    };

    $http(req).success((data, status, headers, config) => {
      if (data.order == null) {
        alert(data.message);
      } else {
        $scope.order = data.order;
        $location.path('/orders/' + $scope.order.orderId);
      }
    }).error(() => {
      alert("Checkout failed...");
    });
  };
}]);

vertxApp.controller('HeaderCtrl', ['$scope', '$http', '$rootScope', '$location',
  function ($scope, $http, $rootScope, $location) {
    $scope.authUrl = '/uaa';
    $scope.user = {};
    $rootScope.user = {};

    $scope.logout = function () {
      $http.post('/logout', {}).success(() => {
        $rootScope.authenticated = false;
        $scope.user = {};
        $rootScope.user = {};
        $location.path("/");
        $location.reload($location.path);
        $rootScope.$broadcast('logout', "update");
      }).error(() => {
        $scope.user = {};
        $rootScope.$broadcast('logout', "update");
      });
    };


    var fetchUser = function () {
      $http({
        method: 'GET',
        url: $scope.authUrl
      }).success((data, status, headers, config) => {
        $scope.user = data;
        $rootScope.authenticated = true;
        $rootScope.user = data;
      }).error(() => {
        scope.user = {};
        $rootScope.authenticated = false;
      });
    };

    fetchUser();
  }]);

vertxApp.controller('AccountCtrl', ['$scope', '$http',
  function ($scope, $http) {
    $scope.url = '/uaa';
    $scope.user = {};

    var fetchUser = function () {
      $http({
        method: 'GET',
        url: $scope.url
      }).success(data => {
        $scope.user = data;
      }).error((data, status, headers, config) => {
      });
    };

    fetchUser();
  }]);

vertxApp.controller('OrderDetailCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams',
  function ($scope, $rootScope, $http, $location, $routeParams) {
    $scope.orderItemUrl = '/api/order/orders/' + $routeParams.orderId;
    var fetchOrder = () => {
      $http({
        method: 'GET',
        url: $scope.orderItemUrl
      }).success((data, status, headers, config) => {
        $scope.order = data;
      }).error((data, status, headers, config) => {
        if (status == 404) {
          $location.path('/404');
        }
      });
    };
    if ($rootScope.user && $rootScope.user.id) {
      fetchOrder();
    } else {
      $location.path('/404');
    }
  }]);

vertxApp.controller('UserOrderCtrl', ['$scope', '$rootScope', '$http', '$location',
  function ($scope, $rootScope, $http, $location) {
    $scope.orders = [];

    $scope.userOrderURL = '/api/order/user/' + $rootScope.user.id + "/orders";
    var fetchOrders = () => {
      $http({
        method: 'GET',
        url: $scope.userOrderURL
      }).success(data => {
        $scope.orders = data;
      }).error((data, status, headers, config) => {
        // $location.path('/');
      })
    };
    fetchOrders();
  }]);