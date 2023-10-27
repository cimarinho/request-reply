package br.com.pix.application

import br.com.pix.configuration.RedisMessageSubscriber
import br.com.pix.infrastructure.pix.sendEvent.PixSendEvent
import org.springframework.stereotype.Service

@Service
class PixCommandHandler(
    private val  pixSendEvent: PixSendEvent,
    private val messageSubscriber: RedisMessageSubscriber,
) {
    fun handler(command: PixCommand){
        pixSendEvent.sendMessage(command.toEvent())

        val future = messageSubscriber.subscribeToChannel(command.correlationId)
        val messageRedis = future.join()

        println("Received message: $messageRedis on channel: ${command.correlationId}")
    }
}