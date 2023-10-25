package br.com.requestReply.application

import br.com.requestReply.domain.PixEvent
import br.com.requestReply.infrastructure.pix.kafka.send.PixKafkaProducer
import br.com.requestReply.infrastructure.pix.postgres.PixPostgresRepository
import br.com.requestReply.infrastructure.pix.postgres.Status
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RequestReplyCommandHandler(
    private val  pixKafkaProducer: PixKafkaProducer,
    private val  pixPostgresRepository: PixPostgresRepository,
    @Value("\${waitInSeconds}") private val waitInSeconds: Int,
) {
    fun handler(command: RequestReplyCommand){
        pixKafkaProducer.sendMessage(command.toEvent())
        val id = pixPostgresRepository.save(command.toEvent())

        for (i in 1..waitInSeconds){
            val pix = pixPostgresRepository.findById(id)
            println("buscou")
            if (pix != Status.CREATED) {
                println("retornou")
                break
            }
            Thread.sleep(500)
            println("aguardou")
        }

        println("saiu")
    }
}