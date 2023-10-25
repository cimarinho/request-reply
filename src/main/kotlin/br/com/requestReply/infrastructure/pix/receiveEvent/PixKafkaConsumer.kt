package br.com.requestReply.infrastructure.pix.receiveEvent

import br.com.requestReply.configuration.RedisMessagePublisher
import br.com.requestReply.domain.PixEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class PixKafkaConsumer(
    private val mapper: ObjectMapper,
    private val messagePublisher: RedisMessagePublisher,
) {

    @KafkaListener(topics = ["\${topic.request}"])
    @SendTo
    fun consumer(message: Message<PixEvent>) : PixEvent {
        val pix = message.payload
        println("Consumer correlation=${pix.correlationId} ${message.headers["kafka_receivedPartitionId"]} ${message.headers["kafka_groupId"]}")

        Thread.sleep(2100)
        println("waiting 2s")
        messagePublisher.publish(pix.correlationId, pix.correlationId)
        return  pix
    }
}
