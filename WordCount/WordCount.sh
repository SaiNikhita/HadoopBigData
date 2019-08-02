#Create java project with WordCount class
#Add jar files (Right click on project -> Build path -> configure build path -> Libraries -> add external libraries)
#Save project as jar on desktop (Right click on project -> Export ->Java ->JAR file ->Give name of jar ->finish)
#Create a text file on desktop

hadoop fs -mkdir -p /hd076/wordcount #Create new directory in hdfs filesystem
hadoop fs -put wc.txt /hd076/wordcount/ #Put text file to count words in that directory
hadoop jar /home/hduser076/Desktop/WordCount.jar WordCount /hd076/wordcount/wc /output #Jarpath Driverclass textfilepath outputpath
