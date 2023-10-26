package br.com.requestReply.infrastructure.pix.postgres

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostgresRepository : JpaRepository<PixEventEntity, Long?> {
    fun findByCorrelationId(correlationId: String): PixEventEntity
}

