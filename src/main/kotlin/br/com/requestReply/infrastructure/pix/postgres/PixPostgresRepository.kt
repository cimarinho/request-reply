package br.com.requestReply.infrastructure.pix.postgres

import br.com.requestReply.domain.PixEvent
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

@Component
class PixPostgresRepository(
    private val postgresRepository: PostgresRepository,
    private val entityManager: EntityManager,
) {
    fun save(pixEvent: PixEvent): Long {
        val pixEntity = PixEventEntity.toEntity(pixEvent)
        val entity = postgresRepository.save(pixEntity)
        return if (entity.id != null) entity.id!! else throw Exception("Mapper error")
    }


    fun update(pixEvent: PixEvent) {
        val pixEntity = PixEventEntity.toEntity(pixEvent)
        postgresRepository.saveAndFlush(pixEntity)
    }

    fun findById(id: Long): Status {
        val query = entityManager.createNativeQuery("SELECT status FROM pix WHERE id = $id")
        val result = query.singleResult
        val status = result as String
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