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
        println("Mensagem recebida: ${message}")
        val pix = message.payload
        if (pix.quantity > 2 ){
            Thread.sleep(1000)
            println("waiting 2second")
        } else {
            Thread.sleep(2000)
            println("waiting 4second")
        }
        return  message.payload
    }
}
