package br.com.pix.infrastructure.pix.receiveEvent

import br.com.pix.configuration.RedisMessagePublisher
import br.com.pix.domain.PixEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class PixConsumerEvent(
    private val messagePublisher: RedisMessagePublisher,
) {

    @KafkaListener(topics = ["\${topic.request}"])
    @SendTo
    fun consumer(message: Message<PixEvent>) {
        val pix = message.payload
        println("Consumer correlation=${pix.correlationId} ${message.headers["kafka_receivedPartitionId"]} ${message.headers["kafka_groupId"]}")
        Thread.sleep(200)
        messagePublisher.publish(pix.correlationId, pix.correlationId)
    }
}
