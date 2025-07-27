package name.snosminin.socialzone.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator testRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("rewrite_path_posting", r -> r
                        .path("/**")
                        .filters(f -> f.rewritePath("/(?<path>.*)", "/${path}"))
                        .uri("http://posting-service:8080"))
                .route("rewrite_path_auth", r -> r
                        .path("/auth/**")
                        .filters(f -> f.rewritePath("/(?<path>.*)", "/${path}"))
                        .uri("http://auth-service:8282"))
                .build();
    }
}
