package br.com.requestReply.infrastructure.pix.receiveEvent

import br.com.requestReply.domain.PixEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class PixKafkaConsumer(
    private val mapper: ObjectMapper,
) {

    @KafkaListener(topics = ["pix_example_spring_template_topic"])
    @SendTo
    fun consumer(message: Message<PixEvent>) : PixEvent {
        val pix = message.payload
        println("correlation=${pix.correlationId} ${message.headers["kafka_receivedPartitionId"]} ${message.headers["kafka_groupId"]}")

        Thread.sleep(100)
        return  pix
    }
}
