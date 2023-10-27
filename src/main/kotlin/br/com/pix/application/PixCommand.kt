package br.com.pix.application

import br.com.pix.domain.PixEvent

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
