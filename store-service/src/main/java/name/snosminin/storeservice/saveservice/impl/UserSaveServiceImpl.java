package name.snosminin.storeservice.saveservice.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.snosminin.storeservice.model.User;
import name.snosminin.storeservice.repository.UserRepository;
import name.snosminin.storeservice.saveservice.UserSaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSaveServiceImpl implements UserSaveService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void add(@NotNull @Valid User user) {
        log.info("Saving user {}", user);
        user.setPasswordHash("password");

        userRepository.add(user);
    }
}
