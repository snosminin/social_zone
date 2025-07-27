package name.snosminin.postingservice.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.postingservice.dto.CreateLikeDto;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import name.snosminin.postingservice.service.LikeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final KafkaTemplate<String, CreateLikeDto> createLikeDtoKafkaTemplate;


    @Transactional
    @Override
    public void add(@NotNull @Valid CreateLikeDto dto) {
        log.info("Sending like {}", dto);
        var record =
                ProducerRecordHelper.getProducerRecord("create-like-topic", dto);
        createLikeDtoKafkaTemplate.send(record);
        log.info("Sent like {}", dto);
    }
}
