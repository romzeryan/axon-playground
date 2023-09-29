docker rm axon_server &&
docker run \
  -d \
  --name axon_server \
  -p 8024:8024 \
  -p 8124:8124 \
  -e AXONIQ_AXONSERVER_NAME=flights_demo \
  axoniq/axonserver:4.5.7-dev-nonroot-arm