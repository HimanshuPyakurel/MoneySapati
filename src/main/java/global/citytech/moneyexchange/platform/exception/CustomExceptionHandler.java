package global.citytech.moneyexchange.platform.exception;

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

    @Override
    public HttpResponse<String> handle(HttpRequest request, CustomException exception) {
        // Customize the response when a CustomException is thrown
        int code = exception.getCode();
        String message = exception.getMessage();
        // Create a JSON response or any other response format you need
        String jsonResponse = String.format("{\"code\": %d, \"message\": \"%s\"}", code, message);
        return HttpResponse.badRequest().body(jsonResponse);
    }


}

