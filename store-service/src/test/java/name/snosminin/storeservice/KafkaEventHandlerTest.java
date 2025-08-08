package name.snosminin.storeservice;

import name.snosminin.storeservice.dto.CreateUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Tag("testcontainers")
@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.kafka.consumer.auto-offset-reset=earliest",
                "jdbc:tc:postgresql:14.7-alpine:///social_zoe_d",
        }
)
@Testcontainers
class KafkaEventHandlerTest {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.9.1")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, CreateUserDto> kafkaTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testKafkaEvent() {

    }
}