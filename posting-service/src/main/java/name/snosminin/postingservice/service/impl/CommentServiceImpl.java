package name.snosminin.postingservice.service.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.postingservice.dto.CreateCommentDto;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import name.snosminin.postingservice.service.CommentService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final KafkaTemplate<String, CreateCommentDto> createCommentDtoKafkaTemplate;


    @Transactional
    @Override
    public void add(@NotNull @Valid CreateCommentDto dto) {
        log.info("Sending comment {}", dto);
        var record =
                ProducerRecordHelper.getProducerRecord("create-comment-topic", dto);
        createCommentDtoKafkaTemplate.send(record);
        log.info("Sent comment {}", dto);
    }
}
