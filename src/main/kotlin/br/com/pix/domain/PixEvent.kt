package br.com.pix.domain

data class PixEvent(
    override val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String
): Event