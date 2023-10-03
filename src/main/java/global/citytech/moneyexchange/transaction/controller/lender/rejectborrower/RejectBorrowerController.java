package global.citytech.moneyexchange.transaction.controller.lender.rejectborrower;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.transaction.service.lender.approveborrower.ApproveBorrowerService;
import global.citytech.moneyexchange.transaction.service.lender.approveborrower.ApprovedTransactionRequest;
import global.citytech.moneyexchange.transaction.service.lender.rejectborrower.RejectBorrowerRequest;
import global.citytech.moneyexchange.transaction.service.lender.rejectborrower.RejectBorrowerService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/lender")
public class RejectBorrowerController {
    public final RejectBorrowerService rejectBorrowerService;

    @Inject
    public RejectBorrowerController(RejectBorrowerService rejectBorrowerService){
        this.rejectBorrowerService = rejectBorrowerService;
    }

    @Post("/reject")
    public CustomResponse rejectBorrowerRequest(@Body RejectBorrowerRequest request){
        rejectBorrowerService.rejectTransaction(request);
        return new CustomResponse("Successfully rejected borrower requested money",true);
    }

}
