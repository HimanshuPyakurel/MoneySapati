package global.citytech.moneyexchange.user.controller.login;

import global.citytech.moneyexchange.user.service.login.UserLoginService;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user")
public class UserLoginController {

    private UserLoginService userLoginService;

    @Inject
    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService=userLoginService;
    }

    @Post("/login")
    public HttpResponse<String> loginUser(@Body UsersDTO usersDto){
        return userLoginService.loginUser(usersDto);
    }

}
