package br.com.pix.infrastructure.pix.receiveEvent

import br.com.pix.configuration.RedisMessagePublisher
import br.com.pix.domain.PixEvent
import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.Function

@Service
class PixConsumerEvent(
    private val messagePublisher: RedisMessagePublisher,
)  {
    @Bean
    fun receivePixEvent(): java.util.function.Function<Flux<Message<PixEvent>>, Flux<Message<PixEvent>>> =
        Function { stream ->
            stream.concatMap {
                println("PixConsumer correlationID ${it.payload.correlationId}")
                Thread.sleep(1500)
                messagePublisher.publish(it.payload.correlationId, it.payload.correlationId)
                mono {
                    it.payload
                }
            }.map {
                MessageBuilder.withPayload(it).setHeader("correlation_id", it.correlationId).build()
            }
        }
}
