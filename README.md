step 1. Start the zookeeper server

bin/zookeeper-server-start.bat config/zookeeper.properties
D:\kafka_2.13-2.6.0\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties

step 2. start the kafka server(kafka broker)

bin/kafka-server-start.bat config/server.properties
D:\kafka_2.13-2.6.0\bin\windows>kafka-server-start.bat ..\..\config\server.properties

step 3. creating a topic (topic managment tool)

D:\kafka_2.13-2.6.0\bin\windows>kafka-topics.bat --create --topic myProductTopic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092