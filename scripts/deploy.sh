##!/bin/bash

BUILD_JAR=$(ls /home/ubuntu/app/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo ">>> build file name: $JAR_NAME" >> /home/ubuntu/deploy.log

echo ">>> copy build file" >> /home/ubuntu/deploy.log
DEPLOY_PATH=/home/ubuntu/app/
cp $BUILD_JAR $DEPLOY_PATH

echo ">>> Check the currently running application pid and close it in bulk" >> /home/ubuntu/deploy.log
sudo ps -ef | grep java | awk '{print $2}' | xargs kill -15

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo ">>> Deploy DEPLOY_JAR"    >> /home/ubuntu/deploy.log
echo ">>> Run $JAR_NAME of $DEPLOY_JAR" >> /home/ubuntu/deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ubuntu/deploy.log 2> /home/ubuntu/deploy_err.log &