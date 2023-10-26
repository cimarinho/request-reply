package br.com.requestReply.antiCorruptionLayer.kafka.configuration

import br.com.requestReply.domain.PixEvent
import br.com.requestReply.infrastructure.pix.sendEvent.PixSendEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import java.util.function.Supplier


@Configuration
class KafkaConfiguration(
    private val pixSendEvent: PixSendEvent,
) {

    @Bean
    fun sendPixEvent(): Supplier<Flux<Message<PixEvent>>> = Supplier {
        pixSendEvent.getFlow().asFlux()
    }

}