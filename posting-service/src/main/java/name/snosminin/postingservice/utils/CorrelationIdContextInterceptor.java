package name.snosminin.postingservice.utils;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CorrelationIdContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        var headers = request.getHeaders();
        headers.add(
                CorrelationIdContext.CORRELATION_ID,
                CorrelationIdContextHolder.getContext().getCorrelationId());

        return execution.execute(request, body);
    }
}