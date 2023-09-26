package global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest;

import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;

public interface InitiateTransactionService {

    Transaction initiateTransaction(InitiateTransactionRequest request);
}
