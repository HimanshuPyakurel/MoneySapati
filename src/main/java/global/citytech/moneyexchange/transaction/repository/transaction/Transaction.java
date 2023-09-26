package global.citytech.moneyexchange.transaction.repository.transaction;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Serdeable
@Introspected
@MappedEntity(schema = "public")
@Table(name = "transaction")
public class Transaction {
    @Id
    private int id;
    private int borrowerId;
    private int lenderId;
    private Double requestAmount;
    private float interestRate;
    private int borrowingPeriod;
    private String transactionStatus;
    private LocalDateTime createdAt;

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

    public Double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Double requestAmount) {
        this.requestAmount = requestAmount;
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

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Transaction(int id, int borrowerId, int lenderId, Double requestAmount, float interestRate,
                       int borrowingPeriod, String transactionStatus, LocalDateTime createdAt) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.requestAmount = requestAmount;
        this.interestRate = interestRate;
        this.borrowingPeriod = borrowingPeriod;
        this.transactionStatus = transactionStatus;
        this.createdAt = createdAt;
    }

    public Transaction(){};
}
