#!/bin/bash
cd /home/ubuntu/mikroad_files
mvn clean-install
sudo mv target/MikroAds.war /var/lib/tomcat7/webapps/MikroAds.war