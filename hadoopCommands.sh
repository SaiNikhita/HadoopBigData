#create hadoopo directory
hadoop fs -mkdir /Trial   #http://localhost:50070/explorer.html#/

#copy file in local system to hadoop directory
hadoop fs -put sample.txt /Trial

#copy file from hadoop to local system
hadoop fs -get /Trial/sample.txt /home/hduser076/
