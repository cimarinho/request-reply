package br.com.requestReply.infrastructure.pix.sendEvent

import br.com.requestReply.domain.PixEvent
import br.com.requestReply.antiCorruptionLayer.kafka.configuration.KafkaEventSend
import org.springframework.stereotype.Component

@Component
class PixSendEvent(
) : KafkaEventSend<PixEvent>() {

    override fun send(event: PixEvent) {
        println("init sendMessage")
        sendMessage(event)
        println("==FOI")
    }
}