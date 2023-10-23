# pix

sudo update-alternatives --config java

k6 run --vus 10 --duration 5s script.js
k6 run script.js

sh kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic order_create_topic --from-beginning
sh kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic produceMessage-out-0
sh kafka-topics.sh --list --bootstrap-server localhost:9092
sh kafka-topics.sh --create --topic order_create_topic --bootstrap-server localhost:9092 --partitions <num-particoes> --replication-factor <fator-replicacao>
sh kafka-topics.sh  --create --topic pix_example_spring_template_topic --bootstrap-server localhost:9092 --partitions 3
sh kafka-topics.sh  --create --topic pix_example_spring_reply_topic --bootstrap-server localhost:9092 --partitions 3



linux

kafka-console-consumer --bootstrap-server localhost:9092 --topic order_create_topic --from-beginning --property value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
kafka-console-consumer --bootstrap-server localhost:9092 --topic pix_example_spring_template_topic
kafka-topics --list --bootstrap-server localhost:9092

kafka-topics --create --topic pix_example_spring_reply_topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor <fator-replicacao>


