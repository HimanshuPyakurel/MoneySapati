package global.citytech.moneyexchange.transaction.service.lender.rejectborrower;

import global.citytech.moneyexchange.response.CustomResponse;
public interface RejectBorrowerService {

    CustomResponse rejectTransaction(RejectBorrowerRequest rejectBorrowerRequest);
}
