package global.citytech.moneyexchange.borrower.controller;

import global.citytech.moneyexchange.borrower.dto.BorrowerDto;
import global.citytech.moneyexchange.borrower.model.Borrower;
import global.citytech.moneyexchange.borrower.service.requestamount.RequestAmountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/borrower")
public class BorrowerRequestAmountController {
    private final RequestAmountService requestAmountService;

    @Inject
    public BorrowerRequestAmountController(RequestAmountService requestAmountService){
        this.requestAmountService =requestAmountService;
    }

    @Post("/request")
    public HttpResponse<String> requestAmount(@Body BorrowerDto borrowerDto){
        requestAmountService.createTransaction(borrowerDto);
        return HttpResponse.ok("Transaction Successfully requested");
    }

}
