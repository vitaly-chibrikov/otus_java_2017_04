#!/usr/bin/env bash

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n -jar target/L2.1.jar