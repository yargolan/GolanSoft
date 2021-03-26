#!/bin/bash

docker_name=mongodb

mkdir -p mongodata

docker run -dit -v mongodata:/data --name ${docker_name} -p 27017:27017 mongo

docker logs -f ${docker_name}
