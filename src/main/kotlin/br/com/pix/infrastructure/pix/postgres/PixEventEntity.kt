package br.com.pix.infrastructure.pix.postgres

import br.com.pix.domain.PixEvent
import jakarta.persistence.*

@Entity
@Table(name = "pix", schema = "pix")
data class PixEventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    @Column(name ="correlation_id") val correlationId: String,
    @Enumerated(EnumType.STRING) val status: Status,
    val quantity: Int,
    val amount: String
) {
    constructor() : this("", "", Status.CANCELLED, 0, "")

    constructor(name: String, correlationId: String, status: Status, quantity: Int, amount: String) : this(
        null, name, correlationId, status, quantity, amount
    )

    companion object {
        fun toEntity(pixEvent: PixEvent): PixEventEntity {
            println("pix $pixEvent")
            return PixEventEntity(
                id = pixEvent.id,
                name = pixEvent.name,
                status = pixEvent.status,
                correlationId = pixEvent.correlationId,
                quantity = pixEvent.quantity,
                amount = pixEvent.amount,
            )
        }

    }
}



enum class Status {
    CREATED, SETTLED, CANCELLED
}



