package br.com.requestReply.application

import br.com.requestReply.domain.PixEvent
import br.com.requestReply.infrastructure.pix.postgres.Status

class RequestReplyCommand(
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
)

fun RequestReplyCommand.toEvent(): PixEvent = PixEvent.create(
    correlationId = correlationId,
    name = name,
    quantity = quantity,
    amount = amount,
)
