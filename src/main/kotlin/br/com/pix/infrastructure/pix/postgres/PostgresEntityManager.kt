package br.com.pix.infrastructure.pix.postgres

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

@Component
class PostgresEntityManager(
    private val entityManager: EntityManager,
){

    fun findById(id: Long): String {
        val query = entityManager.createNativeQuery("SELECT status FROM pix WHERE id = $id")
        val result = query.singleResult
        return result as String
    }
}