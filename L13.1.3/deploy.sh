#!/usr/bin/env bash

mvn clean package

cp target/L13.1.2.war ~/apps/jetty-distribution-9.4.6.v20170531/webapps/root.war
