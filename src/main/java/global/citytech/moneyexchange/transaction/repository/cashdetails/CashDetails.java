package global.citytech.moneyexchange.transaction.repository.cashdetails;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
@MappedEntity(schema = "public")
public class CashDetails {

    @Id
    private int id;
    private int borrowerId;
    private int lenderId;
    private double borrowerAmount;
    private double lenderAmount;
    private String Status;

    public CashDetails() {};

    public CashDetails(int id, int borrowerId, int lenderId, double borrowerAmount,
                       double lenderAmount, String status) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.borrowerAmount = borrowerAmount;
        this.lenderAmount = lenderAmount;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getBorrowerAmount() {
        return borrowerAmount;
    }

    public void setBorrowerAmount(double borrowerAmount) {
        this.borrowerAmount = borrowerAmount;
    }

    public double getLenderAmount() {
        return lenderAmount;
    }

    public void setLenderAmount(double lenderAmount) {
        this.lenderAmount = lenderAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
