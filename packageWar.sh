#!/bin/bash
maven clean-install
sudo mv /home/ubuntu/mikroad_files/target/MikroAds.war /var/lib/tomcat7/webapps/MikroAds.war