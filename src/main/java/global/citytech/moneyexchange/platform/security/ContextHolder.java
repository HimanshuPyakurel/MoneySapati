package global.citytech.moneyexchange.platform.security;

public class ContextHolder {
    private static ThreadLocal<RequestContext> context = new ThreadLocal<>();

    public static RequestContext get() {
        if (context == null) {
            throw new IllegalArgumentException("Request Context is not set");
        }
        return context.get();
    }

    public static void set(RequestContext requestContext) {
        if (context == null) {
            context = new ThreadLocal<>();
        }
        context.set(requestContext);
    }

    public static void unset() {
        if (context != null)
            context.remove();
    }
}
