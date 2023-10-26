package br.com.requestReply.infrastructure.pix.sendEvent

import br.com.requestReply.domain.PixEvent
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
) {


    fun sendMessage(message: PixEvent) {

        val record: ProducerRecord<String, PixEvent> = ProducerRecord<String, PixEvent>(
            topicRequest,
            Random().nextInt(3), message.correlationId, message
        )
        record.headers().add(KafkaHeaders.REPLY_TOPIC, topicReply.toByteArray())
        println("correlation ${message.correlationId}")
        val sendAndReceive = kafkaTemplate.sendAndReceive(record);

        val consumer = sendAndReceive.get();

        println("consumerRecord.value()  ${consumer.value()}")
    }

}