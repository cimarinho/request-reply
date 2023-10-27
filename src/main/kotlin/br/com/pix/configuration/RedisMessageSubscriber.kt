package br.com.pix.configuration

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class RedisMessageSubscriber(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    private val messageFutures = mutableMapOf<String, CompletableFuture<Any>>()

    fun subscribeToChannel(channel: String): CompletableFuture<Any> {
        val future = CompletableFuture<Any>()

        val messageListener = object : MessageListener {
            override fun onMessage(message: Message, pattern: ByteArray?) {
                val messageBody = message.body
                future.complete(messageBody)
            }
        }

        stringRedisTemplate.connectionFactory!!.connection.subscribe(messageListener, channel.toByteArray())

        messageFutures[channel] = future
        return future
    }


}
