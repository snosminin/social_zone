package name.snosminin.storeservice.utils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorrelationIdContextFilterConfig {

    @Bean
    public FilterRegistrationBean<CorrelationIdContextFilter> customFilterRegistration() {
        var registrationBean = new FilterRegistrationBean<CorrelationIdContextFilter>();
        registrationBean.setFilter(new CorrelationIdContextFilter());
        registrationBean.setOrder(-1);
        return registrationBean;
    }
}