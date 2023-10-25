package br.com.requestReply.application

import br.com.requestReply.configuration.MessageSubscriber
import br.com.requestReply.infrastructure.pix.sendEvent.PixKafkaProducer
import org.springframework.stereotype.Service

@Service
class RequestReplyCommandHandler(
    private val  pixKafkaProducer: PixKafkaProducer,
    private val messageSubscriber: MessageSubscriber,
) {
    fun handler(command: RequestReplyCommand){
        pixKafkaProducer.sendMessage(command.toEvent())

        val future = messageSubscriber.subscribeToChannel(command.correlationId)
        val messageRedis = future.join()

        println("Received message: $messageRedis on channel: ${command.correlationId}")
    }
}