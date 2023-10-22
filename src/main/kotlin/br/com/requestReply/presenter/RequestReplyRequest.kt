package br.com.requestReply.presenter

import br.com.requestReply.application.RequestReplyCommand
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.money.MonetaryAmount

class RequestReplyRequest(
    @JsonProperty("name") var name: String,
    @JsonProperty("quantity") var quantity: Int,
    @JsonProperty("amount") var amount: MonetaryAmount,
)

fun RequestReplyRequest.toCommand(correlationId: UUID): RequestReplyCommand = RequestReplyCommand(
    correlationId = correlationId,
    name = name,
    quantity = quantity,
    amount = amount,
)