package br.com.requestReply.application

import br.com.requestReply.infrastructure.pix.sendEvent.PixKafkaProducer
import org.springframework.stereotype.Service

@Service
class RequestReplyCommandHandler(
    private val  pixKafkaProducer: PixKafkaProducer,
) {
    fun handler(command: RequestReplyCommand){
        pixKafkaProducer.sendMessage(command.toEvent())
    }
}