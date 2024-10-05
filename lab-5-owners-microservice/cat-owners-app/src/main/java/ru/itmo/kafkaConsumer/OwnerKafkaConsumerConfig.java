package ru.itmo.kafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.itmo.CatOwnerDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class OwnerKafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, CatOwnerDto> ownerConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(CatOwnerDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CatOwnerDto> ownerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CatOwnerDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ownerConsumerFactory());
        return factory;
    }
}
