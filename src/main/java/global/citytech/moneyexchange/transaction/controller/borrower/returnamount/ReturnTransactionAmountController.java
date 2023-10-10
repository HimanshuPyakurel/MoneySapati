package global.citytech.moneyexchange.transaction.controller.borrower.returnamount;

import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount.ReturnTransactionAmountRequest;
import global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount.ReturnTransactionAmountService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/borrower")
public class ReturnTransactionAmountController {
    private ReturnTransactionAmountService returnTransactionAmountService;

    @Inject
    public ReturnTransactionAmountController(ReturnTransactionAmountService returnTransactionAmountService){
        this.returnTransactionAmountService=returnTransactionAmountService;
    }

    @Post("/return")
    public CustomResponse returnAmount(@Body ReturnTransactionAmountRequest returnTransactionAmountRequest){
        returnTransactionAmountService.returnTransaction(returnTransactionAmountRequest);
        return new CustomResponse("Successfully returned money",true);
    }

}
