package global.citytech.moneyexchange.user.controller.login;

import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.platform.response.RestResponse;
import global.citytech.moneyexchange.user.service.login.UserLoginRequest;
import global.citytech.moneyexchange.user.service.login.UserLoginResponse;
import global.citytech.moneyexchange.user.service.login.UserLoginService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller("/user")
public class UserLoginController {

    private UserLoginService userLoginService;

    @Inject
    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService=userLoginService;
    }

    @Post("/login")
    public HttpResponse<RestResponse> loginUser(@Body UserLoginRequest request){
         var response = userLoginService.loginUser(request);
        return HttpResponse.ok(RestResponse.success(response.get()));
    }

}
