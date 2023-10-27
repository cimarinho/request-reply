package br.com.pix.configuration

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisMessagePublisher(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun publish(channel: String, message: Any) {
        stringRedisTemplate.convertAndSend(channel, message)
    }
}