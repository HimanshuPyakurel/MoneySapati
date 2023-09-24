package global.citytech.moneyexchange.response;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CustomResponse {
    private final String message;
    private final boolean successStatus;

    public CustomResponse(String message, boolean successStatus) {
        this.message = message;
        this.successStatus = successStatus;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessStatus() {
        return successStatus;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", successStatus=" + successStatus +
                '}';
    }
}
