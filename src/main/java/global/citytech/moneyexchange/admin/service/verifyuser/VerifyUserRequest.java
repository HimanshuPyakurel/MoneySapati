package global.citytech.moneyexchange.admin.service.verifyuser;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public class VerifyUserRequest {

    private int userId;

    public VerifyUserRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public VerifyUserRequest(){};
}
