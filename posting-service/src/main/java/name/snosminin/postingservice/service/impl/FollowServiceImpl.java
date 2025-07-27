package name.snosminin.postingservice.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.postingservice.dto.CreateFollowDto;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import name.snosminin.postingservice.service.FollowService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final KafkaTemplate<String, CreateFollowDto> createFollowDtoKafkaTemplate;


    @Transactional
    @Override
    public void add(@NotNull @Valid CreateFollowDto dto) {
        log.info("Sending follow {}", dto);
        var record =
                ProducerRecordHelper.getProducerRecord("create-follow-topic", dto);
        createFollowDtoKafkaTemplate.send(record);
        log.info("Sent follow {}", dto);
    }
}
