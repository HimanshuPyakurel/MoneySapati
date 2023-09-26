package global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public class InitiateTransactionRequest {
    private int borrowerId;
    private int lenderId;
    private double requestAmount;
    private float interestRate;
    private int borrowingPeriod;
    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public int getBorrowingPeriod() {
        return borrowingPeriod;
    }

    public void setBorrowingPeriod(int borrowingPeriod) {
        this.borrowingPeriod = borrowingPeriod;
    }

    public InitiateTransactionRequest(int borrowerId, int lenderId, double requestAmount,
                                      float interestRate, int borrowingPeriod) {
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.requestAmount = requestAmount;
        this.interestRate = interestRate;
        this.borrowingPeriod = borrowingPeriod;
    }
}
