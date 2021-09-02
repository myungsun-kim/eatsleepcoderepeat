#!/bin/bash

cd ~/Project

ant

hdfs dfs -put wordcount_input .

hdfs dfs -rm -r wordcount_output
rm -r wordcount_output

hadoop jar ssafy.jar wordcount wordcount_input wordcount_output

hdfs dfs -get wordcount_output .
cat wordcount_output/*
