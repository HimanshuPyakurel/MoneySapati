package global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount;

import global.citytech.moneyexchange.response.CustomResponse;

public interface ReturnTransactionAmountService {
    CustomResponse returnTransaction(ReturnTransactionAmountRequest returnTransactionRequest);
}
