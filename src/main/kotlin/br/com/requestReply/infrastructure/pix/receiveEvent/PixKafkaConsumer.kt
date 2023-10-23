package br.com.requestReply.infrastructure.pix.receiveEvent

import br.com.requestReply.domain.PixEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class PixKafkaConsumer(
    private val mapper: ObjectMapper,
) {

    @KafkaListener(topics = ["\${topic.request}"])
    @SendTo
    fun consumer(message: Message<PixEvent>) : PixEvent {
        val pix = message.payload
        println("correlation=${pix.correlationId} ${message.headers["kafka_receivedPartitionId"]} ${message.headers["kafka_groupId"]}")

        Thread.sleep(100)
        return  pix
    }
}
