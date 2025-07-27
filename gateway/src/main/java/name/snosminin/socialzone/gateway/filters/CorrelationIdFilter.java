package name.snosminin.socialzone.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

    public static final String CORRELATION_ID = "x-correlation-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Correlation id filter is running");
        var request = exchange.getRequest();
        var header = request.getHeaders();

        if (hasCorrelationId(header)) {
            log.info("Correlation id already added - {}", header.get(CORRELATION_ID));
        } else {
            request = exchange.getRequest()
                    .mutate()
                    .header(CORRELATION_ID, generateCorrelationId())
                    .build();
            log.info("Add correlation id");
            return chain.filter(exchange.mutate().request(request).build());
        }

        return chain.filter(exchange);
    }

    private boolean hasCorrelationId(HttpHeaders header) {
        return header.containsKey(CORRELATION_ID);
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}