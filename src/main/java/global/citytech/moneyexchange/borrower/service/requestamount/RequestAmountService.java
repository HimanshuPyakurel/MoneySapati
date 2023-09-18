package global.citytech.moneyexchange.borrower.service.requestamount;

import global.citytech.moneyexchange.borrower.dto.BorrowerDto;
import global.citytech.moneyexchange.borrower.model.Borrower;
import io.micronaut.http.HttpResponse;

public interface RequestAmountService {
    Borrower createTransaction(BorrowerDto borrowerDto);
}
