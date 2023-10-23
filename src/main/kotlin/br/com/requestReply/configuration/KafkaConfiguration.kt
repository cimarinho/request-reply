package br.com.requestReply.configuration

import br.com.requestReply.domain.PixEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class KafkaConfiguration {

    @Bean
    fun producerFactory(): ProducerFactory<String, PixEvent> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = org.springframework.kafka.support.serializer.JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, PixEvent> {
        return KafkaTemplate(producerFactory())
    }


//    @Bean
//    fun replyContainer(cf: ConsumerFactory<String, PixEvent>): KafkaMessageListenerContainer<String, PixEvent> {
//        val containerProperties = ContainerProperties("pix_example_spring_template_topic")
//        return KafkaMessageListenerContainer<String, PixEvent>(cf, containerProperties)
//    }
//
//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, PixEvent> {
//        val props: MutableMap<String, Any> = HashMap()
//        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
//        props[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"
//
//        return DefaultKafkaConsumerFactory(
//            props, StringDeserializer(), JsonDeserializer()
//        )
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PixEvent>> {
//        val factory: ConcurrentKafkaListenerContainerFactory<String, PixEvent> =
//            ConcurrentKafkaListenerContainerFactory<String, PixEvent>()
//        factory.setConsumerFactory(consumerFactory())
//        factory.setReplyTemplate(kafkaTemplate())
//        return factory
//    }


//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, PixEvent> {
//        val props: MutableMap<String, Any> = HashMap()
//        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
//        props[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"
////        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = org.apache.kafka.common.serialization.StringDeserializer::class.java
////        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = org.springframework.kafka.support.serializer.JsonDeserializer::class.java
//        return DefaultKafkaConsumerFactory(props, StringDeserializer(), JsonDeserializer())
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, PixEvent> {
//        val factory = ConcurrentKafkaListenerContainerFactory<String, PixEvent>()
////        factory.consumerFactory = consumerFactory()
//        return factory
//    }

}