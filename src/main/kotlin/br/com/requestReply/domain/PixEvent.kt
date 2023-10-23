package br.com.requestReply.domain

data class PixEvent(
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
)