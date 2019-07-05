#create hadoopo directory
hadoop fs -mkdir /Trial   #http://localhost:50070/explorer.html#/

#copy file in local system to hadoop directory
hadoop fs -put sample.txt /Trial
