# pix init

docker-compose up<br>
./gradlew bootRun --continue<br>

# execute
curl --location 'http://localhost:8080/api/pix' \
--header 'Content-Type: application/json' \
--data '{
"name": "Marinho",
"quantity": 1,
"amount": "10.5"
}'

# kafka command
sh kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic order_create_topic --from-beginning<br>
sh kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic produceMessage-out-0<br>
sh kafka-topics.sh --list --bootstrap-server localhost:9092<br>
sh kafka-topics.sh --create --topic order_create_topic --bootstrap-server localhost:9092 --partitions <num-particoes> --replication-factor <fator-replicacao><br>
sh kafka-topics.sh  --create --topic pix_example_spring_template_topic --bootstrap-server localhost:9092 --partitions 3<br>
sh kafka-topics.sh  --create --topic pix_example_spring_template_topic --bootstrap-server localhost:9092 --partitions 3<br>
kafka-console-consumer --bootstrap-server localhost:9092 --topic pix_example_spring_template_topic --from-beginning --property value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer<br>
kafka-console-consumer --bootstrap-server localhost:9092 --topic pix_example_spring_template_topic<br>
kafka-topics --list --bootstrap-server localhost:9092<br>
kafka-topics --create --topic pix_example_spring_reply_topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor <fator-replicacao><br>

kafka-topics --describe --topic pix_example_spring_template_topic --bootstrap-server localhost:9092
kafka-topics --describe --topic pix_example_spring_reply_topic --bootstrap-server localhost:9092

kafka-topics --alter --topic pix_example_spring_template_topic --partitions 3 --bootstrap-server localhost:9092
kafka-topics --alter --topic pix_example_spring_reply_topic --partitions 3 --bootstrap-server localhost:9092

# k6 run
k6 run --vus 10 --duration 5s script.js<br>
k6 run --duration 5s script.js<br>

# jvm
sudo update-alternatives --config java

# Aumentando o número de partição
Executar os comando abaixo no kafka<br>
kafka-topics --alter --topic pix_example_spring_template_topic --partitions 5 --bootstrap-server localhost:9092<br>
kafka-topics --alter --topic pix_example_spring_reply_topic --partitions 5 --bootstrap-server localhost:9092<br>

No application yaml mudar para   partition: 5<br>
Descomentar o código na classe  PixProducer e comentar co código logo abaixo<br>
