package global.citytech.moneyexchange.admin.controller.verifyusercontroller;

import global.citytech.moneyexchange.admin.service.verifyuser.VerifyUserRequest;
import global.citytech.moneyexchange.admin.service.verifyuser.VerifyUserService;
import global.citytech.moneyexchange.response.CustomResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/user")
public class VerifyUsersController {
    private VerifyUserService verifyUserService;

    @Inject
    public VerifyUsersController(VerifyUserService verifyUserService){
        this.verifyUserService = verifyUserService;
    }

    @Post("/verify")
    public CustomResponse verifyUser(@Body VerifyUserRequest verifyUserRequest){
        verifyUserService.verifyUser(verifyUserRequest);
        return new CustomResponse("Verified User Successfully",true);
    }



}
