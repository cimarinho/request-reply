package br.com.requestReply.antiCorruptionLayer.kafka.configuration

import br.com.requestReply.domain.Event
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.Message
import reactor.core.publisher.Sinks

abstract class KafkaEventSend<T: Event>  {

    val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<T>>()

    fun getFlow() = unicastProcessor
    abstract fun send(event: T)

    protected fun sendMessage(event: T)  {
        val message =  MessageBuilder.withPayload(event).setHeader("correlation_id",event.correlationId).build()
        unicastProcessor.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}