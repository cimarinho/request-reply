package br.com.requestReply.infrastructure.pix.sendEvent

import br.com.requestReply.domain.PixEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Service
import java.util.*

@Service
class PixKafkaProducer(
    private val kafkaTemplate: ReplyingKafkaTemplate<String, PixEvent, PixEvent>
) {
    private val topic = "pix_example_spring_template_topic"

    private val requestReplyTopic = "pix_example_spring_reply_topic"

    fun sendMessage(message: PixEvent) {

        val record: ProducerRecord<String, PixEvent> = ProducerRecord<String, PixEvent>(topic,
            Random().nextInt(3) , message.correlationId,  message)
        record.headers().add(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.toByteArray())
        println("correlation ${message.correlationId}")
        val sendAndReceive = kafkaTemplate.sendAndReceive(record);
        val sendResult = sendAndReceive.getSendFuture().get();

        val consumer = sendAndReceive.get();

//        consumer.headers().forEach{
//            println("header ${it}")
//        }
//
        println("consumerRecord.value()  ${consumer.value()}")
    }

}