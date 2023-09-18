package global.citytech.moneyexchange.borrower.dto;

import global.citytech.moneyexchange.borrower.model.RequestedMoneyEnum;
import global.citytech.moneyexchange.user.model.Users;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
@Introspected
public class BorrowerDto {
    private int id;
    private BigDecimal requestAmount;
    private RequestedMoneyEnum requestedMoneyStatus;
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

    public BorrowerDto(int id, BigDecimal requestAmount, RequestedMoneyEnum requestedMoneyStatus,
                       Users users) {
        this.id = id;
        this.requestAmount = requestAmount;
        this.requestedMoneyStatus = requestedMoneyStatus;
        this.users = users;
    }

    public BorrowerDto(){};
}
