package global.citytech.moneyexchange.lender.model;

import global.citytech.moneyexchange.user.model.Users;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@SerdeImport
@Introspected
@MappedEntity(schema = "public")
@Table(name = "lender")
public class Lender {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private BigDecimal availableBalance;
    private BigDecimal interestRate;
    private String returnBalanceDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getReturnBalanceDate() {
        return returnBalanceDate;
    }

    public void setReturnBalanceDate(String returnBalanceDate) {
        this.returnBalanceDate = returnBalanceDate;
    }

    public Lender(int id, Users users, BigDecimal availableBalance,
                  BigDecimal interestRate, String returnBalanceDate) {
        this.id = id;
        this.users = users;
        this.availableBalance = availableBalance;
        this.interestRate = interestRate;
        this.returnBalanceDate = returnBalanceDate;
    }

    public Lender(){};

}
