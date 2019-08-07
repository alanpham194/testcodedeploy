#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
parentdir="$(dirname "$DIR")"
mkdir -p /home/ubuntu/testcodedeploy/
cp $parentdir/democodedeploy.jar /home/ubuntu/testcodedeploy/democodedeploy.jar
sudo service testcodedeploy restart
