package global.citytech.moneyexchange.admin.controller.rejectusercontroller;

import global.citytech.moneyexchange.admin.service.rejectuser.RejectUserRequest;
import global.citytech.moneyexchange.admin.service.rejectuser.RejectUserService;
import global.citytech.moneyexchange.admin.service.verifyuser.VerifyUserRequest;
import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.platform.response.RestResponse;
import io.micronaut.http.HttpResponse;
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
    public HttpResponse<RestResponse> rejectUser(@Body RejectUserRequest rejectUserRequest){
        var response = rejectUserService.rejectUser(rejectUserRequest);
        return HttpResponse.ok(RestResponse.success(response.get()));
    }


}
