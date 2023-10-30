# Executar os comandos abaixo para rodar local.
docker-compose up<br>
./gradlew bootRun --continue<br>

# Chamada ao sistema
curl --location 'http://localhost:8080/api/pix' \
--header 'Content-Type: application/json' \
--data '{
"name": "Marinho",
"quantity": 1,
"amount": "10.5"
}'


# Para executar os testes, necessário instalar o k6
k6 run --vus 10 --duration 5s script.js<br>
k6 run --duration 5s script.js<br>


# Aumentando o número de partição, 
Baixar o kafka e navegar ate a pasta bin.<br>
Executar os comando abaixo no kafka<br>
sh kafka-topics.sh --alter --topic pix_example_spring_template_topic --partitions 5 --bootstrap-server localhost:9092<br>
sh kafka-topics.sh --alter --topic pix_example_spring_reply_topic --partitions 5 --bootstrap-server localhost:9092<br>
