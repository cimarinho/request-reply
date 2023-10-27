package br.com.pix.domain

data class PixEvent(
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
)