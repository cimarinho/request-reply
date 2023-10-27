package br.com.pix.infrastructure.pix.sendEvent

import br.com.pix.domain.PixEvent
import br.com.pix.infrastructure.kafka.KafkaEventSend
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