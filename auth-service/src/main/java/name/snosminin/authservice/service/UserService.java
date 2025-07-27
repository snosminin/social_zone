package name.snosminin.authservice.service;

import name.snosminin.authservice.dto.SignUpRequest;
import name.snosminin.authservice.model.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User create(SignUpRequest request);

    void delete(Long id);
}
