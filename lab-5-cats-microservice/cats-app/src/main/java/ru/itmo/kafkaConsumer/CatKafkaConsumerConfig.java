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
import ru.itmo.CatBefriendDto;
import ru.itmo.CatDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class CatKafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, CatDto> catConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(CatDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CatDto> catKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CatDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(catConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, CatBefriendDto> catBefriendConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(CatBefriendDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CatBefriendDto> catBefriendKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CatBefriendDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(catBefriendConsumerFactory());
        return factory;
    }
}
