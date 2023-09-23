package global.citytech.moneyexchange.user.controller.login;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.user.service.login.UserLoginService;
import global.citytech.moneyexchange.user.dto.UsersDTO;
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
    public CustomResponse loginUser(@Body UsersDTO usersDto){
        userLoginService.loginUser(usersDto);
        return new CustomResponse("Successfully Login",true);
    }

}
