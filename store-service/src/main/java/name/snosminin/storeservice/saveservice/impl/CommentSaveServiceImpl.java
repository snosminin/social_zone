package name.snosminin.storeservice.saveservice.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.model.Comment;
import name.snosminin.storeservice.repository.CommentRepository;
import name.snosminin.storeservice.saveservice.CommentSaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentSaveServiceImpl implements CommentSaveService {

    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void add(@NotNull @Valid Comment comment) {
        log.info("Saving comment: {}", comment);

        commentRepository.add(comment);
    }
}
