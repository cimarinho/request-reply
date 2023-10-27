package br.com.pix.presenter

import br.com.pix.application.PixCommand
import com.fasterxml.jackson.annotation.JsonProperty

class RequestReplyRequest(
    @JsonProperty("name") var name: String,
    @JsonProperty("quantity") var quantity: Int,
    @JsonProperty("amount") var amount: String,
)

fun RequestReplyRequest.toCommand(correlationId: String): PixCommand = PixCommand(
    correlationId = correlationId,
    name = name,
    quantity = quantity,
    amount = amount,
)