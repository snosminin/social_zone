package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.events.model.TestEventModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerTest {


    @KafkaListener(topics = "test-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeTest(
            @Payload TestEventModel model,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed model: {}, correlation id: {}", model, correlationId);
    }
}
