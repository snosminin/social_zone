package name.snosminin.storeservice.saveservice.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.model.Follow;
import name.snosminin.storeservice.repository.FollowRepository;
import name.snosminin.storeservice.saveservice.FollowSaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowSaveServiceImpl implements FollowSaveService {

    private final FollowRepository followRepository;

    @Transactional
    @Override
    public void add(@NotNull @Valid Follow follow) {
        log.info("Saving follow: {}", follow);

        followRepository.add(follow);
    }
}
