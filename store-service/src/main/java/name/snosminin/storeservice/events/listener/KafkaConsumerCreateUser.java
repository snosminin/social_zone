package name.snosminin.storeservice.events.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.dto.CreateUserDto;
import name.snosminin.storeservice.mapper.UserMapper;
import name.snosminin.storeservice.saveservice.UserSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerCreateUser {

    private final UserSaveService userSaveService;
    private final UserMapper userMapper;


    @KafkaListener(topics = "create-user-topic", containerFactory = "kafkaListenerContainerFactoryGeneric")
    public void consumeCreateUser(
            @Payload CreateUserDto dto,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        log.info("Consumed create user: {}, correlation id: {}", dto, correlationId);
        userSaveService.add(userMapper.toEntity(dto));
    }
}
