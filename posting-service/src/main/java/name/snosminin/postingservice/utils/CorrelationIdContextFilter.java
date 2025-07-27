package name.snosminin.postingservice.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CorrelationIdContextFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        var httpServletRequest = (HttpServletRequest) servletRequest;

        CorrelationIdContextHolder.getContext().setCorrelationId(
                httpServletRequest.getHeader(
                        CorrelationIdContext.CORRELATION_ID));

        log.info("CorrelationIdContextFilter Correlation id: {}",
                CorrelationIdContextHolder.getContext().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
