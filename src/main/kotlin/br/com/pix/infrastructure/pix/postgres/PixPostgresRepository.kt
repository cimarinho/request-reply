package br.com.pix.infrastructure.pix.postgres

import br.com.pix.domain.PixEvent
import org.springframework.stereotype.Component

@Component
class PixPostgresRepository(
    private val postgresRepository: PostgresRepository,
    private val postgresEntityManager: PostgresEntityManager,
) {
    fun save(pixEvent: PixEvent): Long {
        val pixEntity = PixEventEntity.toEntity(pixEvent)
        val entity = postgresRepository.save(pixEntity)
        return if (entity.id != null) entity.id!! else throw Exception("Mapper error")
    }

    fun findById(id: Long): Status {
        val status = postgresEntityManager.findById(id)
        return Status.valueOf(status)
    }

    fun findByCorrelationId(correlationId: String): PixEvent {
        val entity = postgresRepository.findByCorrelationId(correlationId)
        return toPixEvent(entity)
    }

    private fun toPixEvent(pixEventEntity: PixEventEntity) = PixEvent.createTransacted(
        id = pixEventEntity.id!!,
        correlationId = pixEventEntity.correlationId,
        name = pixEventEntity.name,
        quantity = pixEventEntity.quantity,
        amount = pixEventEntity.amount,
        status = pixEventEntity.status,
    )
}