package global.citytech.moneyexchange.admin.controller.rejectusercontroller;

import global.citytech.moneyexchange.admin.service.rejectuser.RejectUserRequest;
import global.citytech.moneyexchange.admin.service.rejectuser.RejectUserService;
import global.citytech.moneyexchange.response.CustomResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user")
public class RejectUserController {
    private RejectUserService rejectUserService;

    @Inject
    public RejectUserController(RejectUserService rejectUserService){
        this.rejectUserService = rejectUserService;
    }

    @Post("/reject")
    public CustomResponse rejectUser(@Body RejectUserRequest rejectUserRequest){
        rejectUserService.rejectUser(rejectUserRequest);
        return new CustomResponse("Rejected User Successfully",true);
    }

}
