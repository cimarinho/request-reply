package br.com.requestReply.infrastructure.pix.sendEvent

import br.com.requestReply.domain.PixEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class PixKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, PixEvent>
) {

    private val topic = "pix_example_spring_template_topic"

    fun sendMessage(message: PixEvent) {
        kafkaTemplate.send(topic, message)
    }

}