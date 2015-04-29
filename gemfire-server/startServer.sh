#!/bin/bash

source setenv.sh

# Issue commands to gfsh to start locator and launch a server
echo "Starting locator and server..."
gfsh start locator --name=locator1 --port=41111 --properties-file=gemfire.properties --initial-heap=50m --max-heap=50m

gfsh start server --name=server1 --server-port=0 --classpath=../target/classes --properties-file=gemfire.properties --initial-heap=50m
gfsh start server --name=server2 --server-port=0 --classpath=../target/classes --properties-file=gemfire.properties --initial-heap=50m



echo "Loading sample data..."
java client.TestDataLoader
