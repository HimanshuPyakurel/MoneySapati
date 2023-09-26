package global.citytech.moneyexchange.transaction.service.lender.approveborrower;

import global.citytech.moneyexchange.response.CustomResponse;

public interface ApproveBorrowerService {

    CustomResponse approvedTransaction(ApprovedTransactionRequest approvedTransactionRequest);
}
