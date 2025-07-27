package name.snosminin.storeservice.saveservice.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.model.Like;
import name.snosminin.storeservice.repository.LikeRepository;
import name.snosminin.storeservice.saveservice.LikeSaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeSaveServiceImpl implements LikeSaveService {

    private final LikeRepository likeRepository;

    @Transactional
    @Override
    public void add(@NotNull @Valid Like like) {
        log.info("Saving like: {}", like);

        likeRepository.add(like);
    }
}
