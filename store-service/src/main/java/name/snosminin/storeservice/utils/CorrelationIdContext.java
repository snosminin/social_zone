package name.snosminin.storeservice.utils;

import org.springframework.stereotype.Component;

@Component
public class CorrelationIdContext {
    public static final String CORRELATION_ID = "x-correlation-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<String>();


    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }
}
