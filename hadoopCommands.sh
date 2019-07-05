#create hadoopo directory
hadoop fs -mkdir /Trial   #http://localhost:50070/explorer.html#/

#copy file in local system to hadoop directory
hadoop fs -put sample.txt /Trial

#list files in hadoop directory
hadoop fs -ls /Trial

#read file in hadoop
hadoop fs -cat /Trial/sample.txt

#move from one hadoop directory to other
hadoop fs -mv /Trial/sample.txt /Trial1/

#copy from one hadoop directory to other
hadoop fs -cp /Trial1/sample.txt /Trial/

#copy file from hadoop to local system
hadoop fs -get /Trial/sample.txt /home/hduser076/
