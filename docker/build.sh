#!/usr/bin/env bash

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

docker build -t "my-habitat/api-gateway" $DIR/../api-gateway
#docker build -t "my-habitat/cache-infrastructure" $DIR/../cache-infrastructure
#docker build -t "my-habitat/inventory-microservice" $DIR/../inventory-microservice
#docker build -t "my-habitat/monitor-dashboard" $DIR/../monitor-dashboard
#docker build -t "my-habitat/order-microservice" $DIR/../order-microservice
docker build -t "my-habitat/device-microservice" $DIR/../device-microservice
#docker build -t "my-habitat/shopping-cart-microservice" $DIR/../shopping-cart-microservice
docker build -t "my-habitat/room-microservice" $DIR/../room-microservice
docker build -t "my-habitat/account-microservice" $DIR/../account-microservice