hadoop fs -mkdir /hd076/weblog
hadoop fs -put WeblogDataset.txt /hd076/weblog/
hadoop jar /home/hduser076/Desktop/Weblog.jar WeblogAnalyzer /hd076/weblog/WeblogDataset.txt /output/weblog
