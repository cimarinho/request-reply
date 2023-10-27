package br.com.pix.configuration

import br.com.pix.domain.PixEvent
import br.com.pix.infrastructure.pix.sendEvent.PixSendEvent
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