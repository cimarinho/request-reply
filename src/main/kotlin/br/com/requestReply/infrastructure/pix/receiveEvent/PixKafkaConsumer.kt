package br.com.requestReply.infrastructure.pix.receiveEvent

import br.com.requestReply.domain.PixEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Service


@Service
class PixKafkaConsumer(
    private val mapper: ObjectMapper,
) {

    @KafkaListener(topics = ["pix_example_spring_template_topic"])
    fun consumer(message: Message<Any>) {
        println("Mensagem recebida: ${message.payload.toString()}")
        val pix = mapper.readValue(message.payload.toString(), PixEvent::class.java)
        println("Mensagem pix: ${pix}")
    }

}