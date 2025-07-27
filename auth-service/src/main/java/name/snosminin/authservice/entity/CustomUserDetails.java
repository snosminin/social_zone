package name.snosminin.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import name.snosminin.authservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String name;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public static CustomUserDetails create(final User user) {
        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getUsername(),
                user.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
