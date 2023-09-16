package global.citytech.moneyexchange.user.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.persistence.Column;

@SerdeImport
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
    private UserRoleEnum userRoleEnum;

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

    public UserRoleEnum getUserRole() {
        return userRoleEnum;
    }

    public void setUserRole(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
    }

    public Users(){};

    public Users(int id, String firstName, String lastName, String userName,
                 String email, String password, String citizenship,
                 String checkStatus, String checkBlacklist, UserRoleEnum userRoleEnum
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.citizenship = citizenship;
        this.checkStatus = checkStatus;
        this.checkBlacklist = checkBlacklist;
        this.userRoleEnum = userRoleEnum;
    }
}
