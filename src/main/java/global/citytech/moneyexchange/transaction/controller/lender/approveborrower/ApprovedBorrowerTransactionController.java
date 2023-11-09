package global.citytech.moneyexchange.transaction.controller.lender.approveborrower;

import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.transaction.service.lender.approveborrower.ApproveBorrowerService;
import global.citytech.moneyexchange.transaction.service.lender.approveborrower.ApprovedTransactionRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/lender")
public class ApprovedBorrowerTransactionController {
    public final ApproveBorrowerService approveBorrowerService;

    @Inject
    public ApprovedBorrowerTransactionController(ApproveBorrowerService approveBorrowerService){
        this.approveBorrowerService = approveBorrowerService;
    }

    @Post("/approve")
    public CustomResponse approveBorrowerRequest(@Body ApprovedTransactionRequest request){
        approveBorrowerService.approvedTransaction(request);
        return new CustomResponse("Successfully approved borrower requested money",true);
    }



}
