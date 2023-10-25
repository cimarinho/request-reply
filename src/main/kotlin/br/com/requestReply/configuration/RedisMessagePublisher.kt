package br.com.requestReply.configuration

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisMessagePublisher(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun publish(channel: String, message: String) {
        stringRedisTemplate.convertAndSend(channel, message)
    }
}