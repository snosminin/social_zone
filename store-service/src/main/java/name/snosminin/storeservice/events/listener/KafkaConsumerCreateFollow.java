package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.dto.CreateFollowDto;
import name.snosminin.storeservice.mapper.FollowMapper;
import name.snosminin.storeservice.saveservice.FollowSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerCreateFollow {

    private final FollowSaveService followSaveService;
    private final FollowMapper followMapper;


    @KafkaListener(topics = "create-follow-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeCreateFollow(
            @Payload CreateFollowDto dto,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed create follow: {}, correlation id: {}", dto, correlationId);
        followSaveService.add(followMapper.toEntity(dto));
    }
}
