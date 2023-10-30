package br.com.pix.infrastructure.pix.sendEvent

import br.com.pix.domain.PixEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Service
import java.util.*

@Service
class PixProducer(
    private val kafkaTemplate: ReplyingKafkaTemplate<String, PixEvent, PixEvent>,
    @Value("\${topic.request}") private val topicRequest: String,
    @Value("\${topic.reply}") private val topicReply : String,
    @Value("\${topic.partition}") private val partition : String,
) {


    fun sendMessage(message: PixEvent) {
        println("init  PixProducer =  ${message}topicRequest|${topicRequest},topicReply|${topicReply}")

        val number = Random().nextInt(partition.toInt())
        val record: ProducerRecord<String, PixEvent> = ProducerRecord<String, PixEvent>(
            topicRequest,
            number, message.correlationId, message
        )

        record.headers().add(KafkaHeaders.REPLY_TOPIC, topicReply.toByteArray())
        println("correlation ${message.correlationId}")
        val sendAndReceive = kafkaTemplate.sendAndReceive(record);

        val consumer = sendAndReceive.get();

        println("consumerRecord.value()  ${consumer.value()}")
    }

}