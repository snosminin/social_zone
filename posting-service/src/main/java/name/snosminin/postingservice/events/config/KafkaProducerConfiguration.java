package name.snosminin.postingservice.events.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.properties.spring.json.type.mapping}")
    private String mappings;

    @Bean
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return config;
    }

    @Bean
    public <V> JsonSerializer<V> getValueSerializer() {
        Map<String, Object> config = new HashMap<>();

        config.put(JsonSerializer.TYPE_MAPPINGS, mappings);
        var serializer = new JsonSerializer<V>();
        serializer.configure(config, false);

        return serializer;
    }

    @Bean
    public <V> KafkaTemplate<String, V> sendGeneric() {
        return new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(
                        getConfig(),
                        new StringSerializer(),
                        getValueSerializer()));
    }
}