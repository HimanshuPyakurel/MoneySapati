package global.citytech.moneyexchange.admin.controller.verifyusercontroller;

import global.citytech.moneyexchange.admin.service.verifyuser.VerifyUserRequest;
import global.citytech.moneyexchange.admin.service.verifyuser.VerifyUserService;
import global.citytech.moneyexchange.platform.response.RestResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user")
public class VerifyUsersController {
    private VerifyUserService verifyUserService;

    @Inject
    public VerifyUsersController(VerifyUserService verifyUserService){
        this.verifyUserService = verifyUserService;
    }

    @Post("/verify")
    public HttpResponse<RestResponse> verifyUser(@Body VerifyUserRequest verifyUserRequest) {
        var response = verifyUserService.verifyUser(verifyUserRequest);
        return HttpResponse.ok(RestResponse.success(response.get()));
    }



}
