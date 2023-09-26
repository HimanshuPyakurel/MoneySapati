package global.citytech.moneyexchange.transaction.service.lender.approveborrower;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public class ApprovedTransactionRequest {

    private int transactionId;
    private int lenderId;
    private int borrowerId;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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


    public ApprovedTransactionRequest(int transactionId, int lenderId, int borrowerId) {
        this.transactionId = transactionId;
        this.lenderId = lenderId;
        this.borrowerId = borrowerId;
    }

    public ApprovedTransactionRequest(){};
}
