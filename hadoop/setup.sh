#!/bin/bash

username=$(whoami)
sudo chown -R $(whoami):$(whoami) .
sudo mv hadoop-3.2.2 /usr/local/hadoop
sudo apt update
sudo apt install ssh openjdk-8-jdk ant -y
source ~/.bashrc

./set_hadoop_env.sh
./init_hadoop.sh

echo "Please refresh your bash shell. -> source ~/.bashrc"


