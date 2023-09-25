package global.citytech.moneyexchange.response;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CustomResponse {
    private final String message;
    private final boolean isSuccess;

    public CustomResponse(String message,boolean isSuccess) {
        this.message = message;
        this.isSuccess=isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
