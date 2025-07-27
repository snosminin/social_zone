package name.snosminin.postingservice.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.postingservice.dto.CreateUserDto;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import name.snosminin.postingservice.service.UserService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KafkaTemplate<String, CreateUserDto> createUserDtoKafkaTemplate;


    @Override
    public void add(@NotNull @Valid CreateUserDto dto) {
        log.info("Sending user {}", dto);
        var record =
                ProducerRecordHelper.getProducerRecord("create-user-topic", dto);
        createUserDtoKafkaTemplate.send(record);
        log.info("Sent user {}", dto);
    }
}
