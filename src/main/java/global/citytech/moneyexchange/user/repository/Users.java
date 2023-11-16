package global.citytech.moneyexchange.user.repository;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;

@Serdeable
@Introspected
@MappedEntity(schema = "public")
public class Users {
    @Id
    private int id;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    @Column(unique = true)
    private String userName;
    @Nullable
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String citizenship;

    @Nullable
    private String checkStatus;

    @Nullable
    private String checkBlacklist;
    @Nullable
    private StatusAndRoleEnum userRole;

    @Nullable
    private double availableBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckBlacklist() {
        return checkBlacklist;
    }

    public void setCheckBlacklist(String checkBlacklist) {
        this.checkBlacklist = checkBlacklist;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public StatusAndRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(StatusAndRoleEnum userRoleEnum) {
        this.userRole = userRoleEnum;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Users(int id, String firstName, String lastName, String userName, String email,
                 String password, String citizenship, String checkStatus, String checkBlacklist,
                 StatusAndRoleEnum userRole, double availableBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.citizenship = citizenship;
        this.checkStatus = checkStatus;
        this.checkBlacklist = checkBlacklist;
        this.userRole = userRole;
        this.availableBalance = availableBalance;
    }

    public Users(){};


}
