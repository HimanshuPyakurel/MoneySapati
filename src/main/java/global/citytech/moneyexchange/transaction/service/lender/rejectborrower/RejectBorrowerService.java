package global.citytech.moneyexchange.transaction.service.lender.rejectborrower;

import global.citytech.moneyexchange.platform.response.CustomResponse;
public interface RejectBorrowerService {

    CustomResponse rejectTransaction(RejectBorrowerRequest rejectBorrowerRequest);
}
