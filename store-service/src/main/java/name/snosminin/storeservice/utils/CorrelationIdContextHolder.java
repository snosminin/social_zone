package name.snosminin.storeservice.utils;

public class CorrelationIdContextHolder {

    private static final ThreadLocal<CorrelationIdContext> userContext = new ThreadLocal<CorrelationIdContext>();

    public static final CorrelationIdContext getContext() {
        var context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final CorrelationIdContext createEmptyContext() {
        return new CorrelationIdContext();
    }
}
