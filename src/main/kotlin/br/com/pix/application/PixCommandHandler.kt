package br.com.pix.application

import br.com.pix.infrastructure.pix.sendEvent.PixProducer
import org.springframework.stereotype.Service

@Service
class PixCommandHandler(
    private val  pixKafkaProducer: PixProducer,
) {
    fun handler(command: PixCommand){
        pixKafkaProducer.sendMessage(command.toEvent())
    }
}