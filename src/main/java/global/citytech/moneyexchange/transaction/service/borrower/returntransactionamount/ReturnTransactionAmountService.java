package global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount;

import global.citytech.moneyexchange.platform.response.CustomResponse;

public interface ReturnTransactionAmountService {
    CustomResponse returnTransaction(ReturnTransactionAmountRequest returnTransactionRequest);
}
