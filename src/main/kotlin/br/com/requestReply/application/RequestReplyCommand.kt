package br.com.requestReply.application

import br.com.requestReply.domain.PixEvent

class RequestReplyCommand(
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
)

fun RequestReplyCommand.toEvent(): PixEvent = PixEvent(
    correlationId = correlationId,
    name = name,
    quantity = quantity,
    amount = amount,
)