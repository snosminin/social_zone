package name.snosminin.storeservice.events.config;

import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@NoArgsConstructor
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keySerializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueSerializer;

    @Value("${spring.kafka.consumer.properties.spring.json.type.mapping}")
    private String mappings;

    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerializer);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerializer);

        return config;
    }

    public <V> ConsumerFactory<String, V> consumerFactory() {
        JsonDeserializer<V> jsonDeserializer = new JsonDeserializer<>();
        Map<String, Object> configDes = new HashMap<>();
        configDes.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configDes.put(JsonDeserializer.TYPE_MAPPINGS, mappings);
        jsonDeserializer.configure(configDes, false);

        return new DefaultKafkaConsumerFactory<>(
                getConfig(),
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public <V> ConcurrentKafkaListenerContainerFactory<String, V> kafkaListenerContainerFactoryGeneric() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, V>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}