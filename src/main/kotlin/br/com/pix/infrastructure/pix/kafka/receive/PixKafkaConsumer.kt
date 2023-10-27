package br.com.pix.infrastructure.pix.kafka.receive

import br.com.pix.domain.PixEvent
import br.com.pix.infrastructure.pix.postgres.PixPostgresRepository
import br.com.pix.infrastructure.pix.postgres.Status
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class PixKafkaConsumer(
    private val pixPostgresRepository: PixPostgresRepository,
) {

    @KafkaListener(topics = ["\${topic.request}"])
    @SendTo
    fun consumer(message: Message<PixEvent>) {
        val pix = message.payload
        println("Consumer correlation=${pix.correlationId} ${message.headers["kafka_receivedPartitionId"]} ${message.headers["kafka_groupId"]}")

        Thread.sleep(2000)
        println("waiting 2s")

        val pixEvent = pixPostgresRepository.findByCorrelationId(pix.correlationId)
        pixEvent.updateStatus(Status.SETTLED)
        pixPostgresRepository.save(pixEvent)
    }
}
