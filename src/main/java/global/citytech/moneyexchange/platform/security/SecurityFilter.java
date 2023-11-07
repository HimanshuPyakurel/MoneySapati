package global.citytech.moneyexchange.platform.security;

import global.citytech.moneyexchange.platform.response.RestResponse;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import java.util.Objects;
import java.util.logging.Logger;


@Filter("/**")
public class SecurityFilter implements HttpServerFilter {
    private final String TOKEN = "X-XSRF-TOKEN";
    private final SecurityUtils securityUtils;

    private final Logger logger = Logger.getLogger(SecurityFilter.class.getName());

    @Inject
    public SecurityFilter(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        try {
            if (request.getMethod() == HttpMethod.OPTIONS)
                return Flowable.just(HttpResponse.ok());

            var token = request.getHeaders().get(TOKEN);

            if (request.getPath().contains("/user/signup")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/rest-api/http-client-test")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("user/login")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/borrower/request")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/borrower/return")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/lender/approve")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/user/transaction-list")) {
                return chain.proceed(request);
            }
            if (request.getPath().contains("/lender/rate-borrower")) {
                return chain.proceed(request);
            }

            if (Objects.isNull(token) || token.isEmpty()) {
                throw new IllegalArgumentException("Security Token is missing");
            }

            RequestContext requestContext = securityUtils.parseTokenAndGetContext(token);
            logger.info("REQUESTED BY :: " + requestContext.email());

            return Flowable.just(true)
                    .doOnRequest(t -> {
                        ContextHolder.set(requestContext);
                    })
                    .switchMap(aBoolean -> chain.proceed(request))
                    .onErrorReturn(throwable -> {
                        throwable.printStackTrace();
                        logger.info("::: ERROR IN CHAIN PROCESS :::");
                        throw new IllegalArgumentException("Security interceptor Exception");
                    });
        } catch (Exception e) {

            return Flowable.just(HttpResponse.badRequest(RestResponse.error("EX001", e.getMessage())));
        }

    }
}
