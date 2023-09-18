package global.citytech.moneyexchange.borrower.model;

import global.citytech.moneyexchange.user.model.Users;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@SerdeImport
@Introspected
@MappedEntity(schema = "public")
@Table(name = "borrower")
public class Borrower {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private int id;
    private BigDecimal requestAmount;
    private RequestedMoneyEnum requestedMoneyStatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public RequestedMoneyEnum getRequestedMoneyStatus() {
        return requestedMoneyStatus;
    }

    public void setRequestedMoneyStatus(RequestedMoneyEnum requestedMoneyStatus) {
        this.requestedMoneyStatus = requestedMoneyStatus;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Borrower(int id, BigDecimal requestAmount, RequestedMoneyEnum requestedMoneyStatus, Users users) {
        this.id = id;
        this.requestAmount = requestAmount;
        this.requestedMoneyStatus = requestedMoneyStatus;
        this.users = users;
    }

    public Borrower() {
    }

    ;
}
