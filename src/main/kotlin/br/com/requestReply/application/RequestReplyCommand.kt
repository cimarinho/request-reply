package br.com.requestReply.application

import java.util.*
import javax.money.MonetaryAmount

class RequestReplyCommand(
    val correlationId: UUID,
    val name: String,
    val quantity: Int,
    val amount: MonetaryAmount,
)