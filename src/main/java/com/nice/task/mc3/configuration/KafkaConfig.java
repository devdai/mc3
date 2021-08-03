package com.nice.task.mc3.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    //todo Add env variable to config file instead of hardcoding the url
    private static final String BOOTSTRAP_ADDRESS = "kafka:29092";

    /**
     * Create kafka consumer factory.
     *
     * @return ConsumerFactory
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_ADDRESS);
        configProps.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        configProps.put(
                ConsumerConfig.GROUP_ID_CONFIG, "group");
        configProps.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /**
     * Create and init ConcurrentKafkaListenerContainerFactory for kafka listener implementation
     *
     * @return ConcurrentKafkaListenerContainerFactory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

}