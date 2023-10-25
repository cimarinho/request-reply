package br.com.requestReply.infrastructure.pix.sendEvent

import br.com.requestReply.domain.PixEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class PixKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, PixEvent>,
    @Value("\${topic.request}") private val topicRequest: String,
    @Value("\${topic.reply}") private val topicReply: String,
    private val stringRedisTemplate: StringRedisTemplate,

) {

    fun sendMessage(message: PixEvent) {
        println("init sendMessage")
        val record: ProducerRecord<String, PixEvent> = ProducerRecord<String, PixEvent>(
            topicRequest, 0, message.correlationId, message
        )
        kafkaTemplate.send(record)
        println("send kafka correlation ${message.correlationId}")
    }
}