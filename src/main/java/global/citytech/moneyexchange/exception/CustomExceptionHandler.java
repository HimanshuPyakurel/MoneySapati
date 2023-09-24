package global.citytech.moneyexchange.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {CustomException.class, ExceptionHandler.class})
public class CustomExceptionHandler implements ExceptionHandler<CustomException, HttpResponse<String>> {

    private String message;

    @Override
    public HttpResponse<String> handle(HttpRequest request, CustomException exception) {
        return HttpResponse.badRequest().body(exception.getMessage());
    }


}

