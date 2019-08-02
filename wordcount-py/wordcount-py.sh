touch word.txt
hadoop fs -mkdir -p /hd076/wordcount-py
hadoop fs -copyFromLocal word.txt /hd076/wordcount-py
hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar -file mapper.py -mapper mapper.py -file reducer.py -reducer reducer.py -input /hd076/wordcount-py/word.txt -output /output/wordcount-py/
