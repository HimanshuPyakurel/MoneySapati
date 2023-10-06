package global.citytech.moneyexchange.platform.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@Introspected
@Serdeable
public record RestResponse<T>(String code, String message, T data) {

    public static <T> RestResponse success() {
        return RestResponseBuilder.builder()
                .code("0")
                .message("SUCCESS")
                .data(null)
                .build();
    }

    public static <T> RestResponse success(T data) {
        return RestResponseBuilder.builder()
                .code("0")
                .message("SUCCESS")
                .data(data)
                .build();
    }

    public static RestResponse error(String code, String message) {
        return RestResponseBuilder.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static RestResponse error() {
        return RestResponseBuilder.builder()
                .code("400")
                .message("UNSUCCESSFUL")
                .build();
    }
}
