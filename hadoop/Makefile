
init :
	tar zxf Project.tar.gz
	mv Project/hadoop-3.2.2 ./hadoop-3.2.2
	rm -r ./Project
	sudo chown -R $(whoami):$(whoami) .
	sudo mv hadoop-3.2.2 /usr/local/hadoop
	sudo apt update
	sudo apt install ssh openjdk-8-jdk ant -y
	source ~/.bashrc
	make hadoop-setup
	
hadoop-setup : 
	./set_hadoop_env.sh
	./set_ssh.sh
	./init_hadoop.sh

wordcount-init :
	hdfs dfs -mkdir wordcount_test
	hdfs dfs -put ./wordcount_input wordcount_test
	hdfs dfs -mkdir matrix_test
	hdfs dfs -put ./matadd-data-2x2.txt ./matrix_test
	hdfs dfs -mkdir wordcount_test_out
	hdfs dfs -mkdir wordcountsort_test_out
	hdfs dfs -mkdir wordcountinvertedindex_test_out
	hdfs dfs -mkdir matrixadd_test_out

wordcount1char :
	ant
	hdfs dfs -rm -r wordcount_test_out
	hadoop jar ssafy.jar wordcount1char wordcount_test wordcount_test_out
	hdfs dfs -cat wordcount_test_out/part-r-00000 | more
	hdfs dfs -cat wordcount_test_out/part-r-00001 | more

wordcountsort :
	ant
	hdfs dfs -rm -r wordcountsort_test_out
	hadoop jar ssafy.jar wordcountsort wordcount_test wordcountsort_test_out
	hdfs dfs -cat wordcountsort_test_out/part-r-00000 | more
	hdfs dfs -cat wordcountsort_test_out/part-r-00001 | more

matrixadd :
	ant
	hdfs dfs -rm -r matrixadd_test_out
	hadoop jar ssafy.jar matrixadd matrix_test matrixadd_test_out
	hdfs dfs -cat matrixadd_test_out/part-r-00000 | more
	hdfs dfs -cat matrixadd_test_out/part-r-00001 | more

wordcountinvertedindex :
	ant
	hdfs dfs -rm -r wordcountinvertedindex_test_out
	hadoop jar ssafy.jar wordcountinvertedindex wordcount_test wordcountinvertedindex_test_out
	hdfs dfs -cat wordcountinvertedindex_test_out/part-r-00000 | more
	hdfs dfs -cat wordcountinvertedindex_test_out/part-r-00001 | more