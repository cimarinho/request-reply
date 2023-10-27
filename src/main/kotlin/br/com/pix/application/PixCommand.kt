package br.com.pix.application

import br.com.pix.domain.PixEvent

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