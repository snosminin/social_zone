package name.snosminin.postingservice.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.postingservice.dto.CreatePostDto;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import name.snosminin.postingservice.service.PostService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final KafkaTemplate<String, CreatePostDto> createPostDtoKafkaTemplate;


    @Transactional
    @Override
    public void add(@NotNull @Valid CreatePostDto dto) {
        log.info("Sending post {}", dto);
        var record =
                ProducerRecordHelper.getProducerRecord("create-post-topic", dto);
        createPostDtoKafkaTemplate.send(record);
        log.info("Sent post {}", dto);
    }
}