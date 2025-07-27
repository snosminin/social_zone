package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.dto.CreateCommentDto;
import name.snosminin.storeservice.mapper.CommentMapper;
import name.snosminin.storeservice.saveservice.CommentSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerCreateComment {

    private final CommentSaveService commentSaveService;
    private final CommentMapper commentMapper;


    @KafkaListener(topics = "create-comment-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeCreateComment(
            @Payload CreateCommentDto dto,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed create comment: {}, correlation id: {}", dto, correlationId);
        commentSaveService.add(commentMapper.toEntity(dto));
    }
}
