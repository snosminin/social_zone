package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.dto.CreatePostDto;
import name.snosminin.storeservice.mapper.PostMapper;
import name.snosminin.storeservice.saveservice.PostSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerCreatePost {

    private final PostSaveService postSaveService;
    private final PostMapper postMapper;


    @KafkaListener(topics = "create-post-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeCreatePost(
            @Payload CreatePostDto dto,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed create post: {}, correlation id: {}", dto, correlationId);
        postSaveService.add(postMapper.toEntity(dto));
    }
}
