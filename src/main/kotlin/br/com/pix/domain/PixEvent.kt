package br.com.pix.domain

import br.com.pix.infrastructure.pix.postgres.Status

data class PixEvent private constructor(
    val id: Long? = null,
    val correlationId: String,
    val name: String,
    val quantity: Int,
    val amount: String,
) {

    private constructor(
        id: Long,
        correlationId: String,
        name: String,
        quantity: Int,
        amount: String,
        status: Status
    ) : this(id, correlationId, name, quantity, amount) {
        this.status = status
    }

    var status: Status = Status.CREATED
        private set

    fun updateStatus(status: Status) {
        //create a rule to validate the status
        this.status = status
    }

    companion object {

        fun create(
            correlationId: String,
            name: String,
            quantity: Int,
            amount: String,
        ): PixEvent {
            //create a rule to validate the status
            return PixEvent(
                correlationId = correlationId,
                name = name,
                quantity = quantity,
                amount = amount,
            )
        }

        fun createTransacted(
            id: Long,
            correlationId: String,
            name: String,
            quantity: Int,
            amount: String,
            status: Status
        ): PixEvent {
            return PixEvent(
                id = id,
                correlationId = correlationId,
                name = name,
                quantity = quantity,
                amount = amount,
                status = status,
            )
        }
    }

}

enum class Status {
    CREATED, SETTLED, CANCELLED
}