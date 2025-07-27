package name.snosminin.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import name.snosminin.authservice.entity.CustomUserDetails;
import name.snosminin.authservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {

        var user = userService.getByUsername(username);
        return CustomUserDetails.create(user);
    }
}
