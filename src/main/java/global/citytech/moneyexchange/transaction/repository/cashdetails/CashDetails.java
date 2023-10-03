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
    private double borrowerAmountToPay;
    private double totalLenderAvailableAmount;
    private String status;

    public CashDetails() {};

    public CashDetails(int id, int borrowerId, int lenderId,
                       double borrowerAmountToPay, double totalLenderAvailableAmount, String status) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.borrowerAmountToPay = borrowerAmountToPay;
        this.totalLenderAvailableAmount = totalLenderAvailableAmount;
        this.status = status;
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

    public double getBorrowerAmountToPay() {
        return borrowerAmountToPay;
    }

    public void setBorrowerAmountToPay(double borrowerAmountToPay) {
        this.borrowerAmountToPay = borrowerAmountToPay;
    }

    public double getTotalLenderAvailableAmount() {
        return totalLenderAvailableAmount;
    }

    public void setTotalLenderAvailableAmount(double totalLenderAvailableAmount) {
        this.totalLenderAvailableAmount = totalLenderAvailableAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
