package global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public class ReturnTransactionAmountRequest {
    private int cashId;
    private int borrowerId;
    private int lenderId;
    private double returningAmount;
    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public double getReturningAmount() {
        return returningAmount;
    }

    public void setReturningAmount(double returningAmount) {
        this.returningAmount = returningAmount;
    }

    public ReturnTransactionAmountRequest(int cashId, int borrowerId, int lenderId, double returningAmount) {
        this.cashId = cashId;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.returningAmount = returningAmount;
    }

    public ReturnTransactionAmountRequest(){};

}
