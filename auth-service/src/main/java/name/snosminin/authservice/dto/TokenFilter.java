package name.snosminin.authservice.dto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import name.snosminin.authservice.exception.ResourceNotFoundException;
import name.snosminin.authservice.service.impl.JwtServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@AllArgsConstructor
public class TokenFilter extends GenericFilterBean {
    private final JwtServiceImpl jwtServiceImpl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String bearerToken = ((HttpServletRequest) request).getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7);
        }
        if (bearerToken != null && jwtServiceImpl.validateToken(bearerToken)) {
            try {
                Authentication authentication = jwtServiceImpl.getAuthentication(bearerToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ResourceNotFoundException e) {
            }
        }
        chain.doFilter(request, response);
    }
}
