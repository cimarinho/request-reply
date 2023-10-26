package br.com.requestReply.application

import br.com.requestReply.antiCorruptionLayer.redis.RedisMessageSubscriber
import br.com.requestReply.infrastructure.pix.sendEvent.PixSendEvent
import org.springframework.stereotype.Service

@Service
class PixCommandHandler(
    private val  pixSendEvent: PixSendEvent,
    private val messageSubscriber: RedisMessageSubscriber,
) {
    fun handler(command: PixCommand){
        pixSendEvent.send(command.toEvent())

        val future = messageSubscriber.subscribeToChannel(command.correlationId)
        val messageRedis = future.join()

        println("Received message: $messageRedis on channel: ${command.correlationId}")
    }
}