#!/bin/bash
sudo chown -R ubuntu:ubuntu /home/ubuntu/mikroad_files
cd /home/ubuntu/mikroad_files
mvn clean install
sudo mv target/MikroAds.war /var/lib/tomcat7/webapps/MikroAds.war