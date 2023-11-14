package global.citytech.moneyexchange.user.controller.getAll;

import global.citytech.moneyexchange.platform.response.RestResponse;
import global.citytech.moneyexchange.platform.security.ContextHolder;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.service.getall.UserGetAllRequest;
import global.citytech.moneyexchange.user.service.getall.UserGetAllService;
import global.citytech.moneyexchange.user.service.login.UserLoginRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/user")
public class GetAllController {
    private UserGetAllService userGetAllService;

    @Inject
    public GetAllController(UserGetAllService userGetAllService) {
        this.userGetAllService = userGetAllService;
    }

    @Get("/get")
    public HttpResponse<RestResponse> getUser() {
        var response =  userGetAllService.getAllUsers();
        return HttpResponse.ok(RestResponse.success(response));
    }

}
