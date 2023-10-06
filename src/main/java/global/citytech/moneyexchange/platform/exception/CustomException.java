package global.citytech.moneyexchange.platform.exception;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CustomException extends RuntimeException {

    private int code;
    private String message;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
