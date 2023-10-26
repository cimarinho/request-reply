package br.com.requestReply.application

import br.com.requestReply.domain.PixEvent

class PixCommand(
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
)

fun PixCommand.toEvent(): PixEvent = PixEvent(
    correlationId = correlationId,
    name = name,
    quantity = quantity,
    amount = amount,
)