package global.citytech.moneyexchange.user.dto;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public class UsersDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String citizenship;
    private StatusAndRoleEnum userRoleEnum;
    private String checkStatus;
    private String checkBlacklist;

    private double availableBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        return userRoleEnum;
    }

    public void setUserRole(StatusAndRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public UsersDTO(){};

    public UsersDTO(int id, String firstName, String lastName, String userName,
                    String email, String password, String citizenship,
                    StatusAndRoleEnum userRoleEnum, String checkStatus, String checkBlacklist,double AvailableBalance)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.citizenship = citizenship;
        this.userRoleEnum = userRoleEnum;
        this.checkStatus = checkStatus;
        this.checkBlacklist = checkBlacklist;
        this.availableBalance = availableBalance;
    }
}
