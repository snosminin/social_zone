package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.dto.CreateLikeDto;
import name.snosminin.storeservice.mapper.LikeMapper;
import name.snosminin.storeservice.saveservice.LikeSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerCreateLike {

    private final LikeSaveService likeSaveService;
    private final LikeMapper likeMapper;


    @KafkaListener(topics = "create-like-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeCreateLike(
            @Payload CreateLikeDto dto,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed create like: {}, correlation id: {}", dto, correlationId);
        likeSaveService.add(likeMapper.toEntity(dto));
    }
}
