package br.com.pix.configuration

import br.com.pix.domain.PixEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class KafkaConfiguration {

    @Value("\${topic.request}")
    private lateinit var topicRequest: String

    @Value("\${topic.reply}")
    private lateinit var topicReply: String

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrap: String


    @Bean
    fun producerFactory(): ProducerFactory<String, PixEvent> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] =
            org.springframework.kafka.support.serializer.JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, PixEvent> {
        return KafkaTemplate(producerFactory())
    }


    @Bean
    fun replyKafkaTemplate(
        pf: ProducerFactory<String, PixEvent>?,
        container: KafkaMessageListenerContainer<String, PixEvent>?
    ): ReplyingKafkaTemplate<String, PixEvent, PixEvent> {
        return ReplyingKafkaTemplate(pf, container)
    }

    @Bean
    fun replyContainer(cf: ConsumerFactory<String, PixEvent>): KafkaMessageListenerContainer<String, PixEvent> {
        val containerProperties = ContainerProperties(topicReply)
        return KafkaMessageListenerContainer<String, PixEvent>(cf, containerProperties)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PixEvent>> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, PixEvent> =
            ConcurrentKafkaListenerContainerFactory<String, PixEvent>()
        factory.setConsumerFactory(consumerFactory())
        factory.setReplyTemplate(kafkaTemplate())
        return factory
    }


    @Bean
    fun consumerFactory(): ConsumerFactory<String, PixEvent> {
        return DefaultKafkaConsumerFactory<String, PixEvent>(
            consumerConfigs(), StringDeserializer(), JsonDeserializer(
                PixEvent::class.java
            )
        )
    }

    @Bean
    fun consumerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap
        props[ConsumerConfig.GROUP_ID_CONFIG] = topicRequest
        return props
    }
}