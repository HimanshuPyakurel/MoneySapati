package global.citytech.moneyexchange.admin.service.rejectuser;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public class RejectUserRequest {
    private int userId;
    private String adminEmail;
    private String adminPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public RejectUserRequest(int userId, String adminEmail, String adminPassword) {
        this.userId = userId;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }
    public  RejectUserRequest(){};
}
