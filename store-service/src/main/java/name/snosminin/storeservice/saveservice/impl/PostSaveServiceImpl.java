package name.snosminin.storeservice.saveservice.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.model.Post;
import name.snosminin.storeservice.repository.PostRepository;
import name.snosminin.storeservice.saveservice.PostSaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostSaveServiceImpl implements PostSaveService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public void add(@NotNull Post post) {
        log.info("Saving post: {}", post);

        postRepository.add(post);
    }
}