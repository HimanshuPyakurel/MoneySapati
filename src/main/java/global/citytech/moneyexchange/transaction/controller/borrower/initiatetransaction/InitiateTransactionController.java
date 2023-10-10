package global.citytech.moneyexchange.transaction.controller.borrower.initiatetransaction;

import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest.InitiateTransactionRequest;
import global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest.InitiateTransactionService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/transaction")
public class InitiateTransactionController {

    public final InitiateTransactionService initiateTransactionService;

    @Inject
    public InitiateTransactionController(InitiateTransactionService initiateTransactionService){
        this.initiateTransactionService=initiateTransactionService;
    }

    @Post("/request")
    public CustomResponse borrowerRequest(@Body InitiateTransactionRequest request){
        initiateTransactionService.initiateTransaction(request);
        return new CustomResponse("Successfully requested money",true);
    }

}
