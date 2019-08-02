hadoop fs -mkdir /hd076/highesttemperature
hadoop fs -put TemperatureDataset /hd076/highesttemperature
hadoop jar /home/hduser076/Desktop/Temperature.jar HighestDriver /hd076/highesttemperature/TemperatureDataset /output/temperature
