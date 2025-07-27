package name.snosminin.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import name.snosminin.authservice.dto.SignUpRequest;
import name.snosminin.authservice.exception.ResourceNotFoundException;
import name.snosminin.authservice.model.Role;
import name.snosminin.authservice.model.User;
import name.snosminin.authservice.repository.UserRepository;
import name.snosminin.authservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional
    public User create(SignUpRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and Password Conformation do not match.");
        }

        var user = new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
