#!/bin/bash

docker_name=camping-mongo
local_share_dir=/opt/ws/MongoDB/Camping


# Get the current folder full path
cwd=$(pwd)

# Verify that the local folder exists.
mkdir -p data

# Load the docker
docker run --name ${docker_name} -v ${local_share_dir}:/data/db -p 27017:27017 -d mongo --wiredTigerCacheSizeGB 2
