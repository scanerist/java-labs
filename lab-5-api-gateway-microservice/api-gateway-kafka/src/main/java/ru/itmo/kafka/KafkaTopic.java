package ru.itmo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topicCatCreate() {

        return TopicBuilder.name("topicCatCreate").build();
    }

    @Bean
    public NewTopic topicCatUpdate() {

        return TopicBuilder.name("topicCatUpdate").build();
    }

    @Bean
    public NewTopic topicCatDelete() {

        return TopicBuilder.name("topicCatDelete").build();
    }

    @Bean
    public NewTopic topicCatBefriend() {

        return TopicBuilder.name("topicCatBefriend").build();
    }

    @Bean
    public NewTopic topicOwnerCreate() {

        return TopicBuilder.name("topicOwnerCreate").build();
    }

    @Bean
    public NewTopic topicOwnerUpdate() {

        return TopicBuilder.name("topicOwnerUpdate").build();
    }

    @Bean
    public NewTopic topicOwnerDelete() {

        return TopicBuilder.name("topicOwnerDelete").build();
    }
}
