package br.com.requestReply.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import org.javamoney.moneta.Money
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import javax.money.CurrencyUnit
import javax.money.Monetary
import javax.money.MonetaryAmount

@Configuration
open class JacksonConfig  {

    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
        val module = SimpleModule()
        module.addSerializer(Money::class.java, MonetaryAmountSerializer())
        module.addDeserializer(MonetaryAmount::class.java, MonetaryAmountDeserializer())
        objectMapper.registerModule(module)
        return objectMapper
    }
}

open class MonetaryAmountSerializer : JsonSerializer<Money>() {
    override fun serialize(
        value: Money,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        gen.writeStartObject()
        gen.writeStringField("currency", value.currency.currencyCode)
        gen.writeNumberField("amount", value.number.toDouble())
        gen.writeEndObject()
    }
}

open class MonetaryAmountDeserializer : JsonDeserializer<MonetaryAmount>() {
    override fun deserialize(
        jp: JsonParser,
        ctxt: DeserializationContext
    ): MonetaryAmount {
        val node = jp.codec.readTree<com.fasterxml.jackson.databind.JsonNode>(jp)
        val currencyCode = node.get("currency").asText()
        val amount = node.get("amount").asDouble()

        val currencyUnit: CurrencyUnit = Monetary.getCurrency(currencyCode)
        return Money.of(amount, currencyUnit)
    }
}