package br.com.requestReply.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
open class JacksonConfig  {

    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
        val module = SimpleModule()
        objectMapper.registerModule(module)
        return objectMapper
    }
}

