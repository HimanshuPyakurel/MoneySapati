package global.citytech.moneyexchange.user.controller.signup;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.response.RestResponse;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.service.signup.UserSignupService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import java.util.Random;

@Controller("/user")
public class UserSignupController {
    private UserSignupService userSignupService;

    @Inject
    public UserSignupController(UserSignupService userSignupService){
        this.userSignupService = userSignupService;
    }

    @Post("/signup")
    public CustomResponse postSignup(@Body UsersDTO usersDto){
        Random random=new Random();
        usersDto.setId(random.nextInt(10000));
        userSignupService.signup(usersDto);
        return new CustomResponse("User Signup Successful",true);
    }

}
