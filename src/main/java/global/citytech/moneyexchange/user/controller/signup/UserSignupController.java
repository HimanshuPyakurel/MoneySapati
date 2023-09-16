package global.citytech.moneyexchange.user.controller.signup;

import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.service.signup.UserSignupService;
import io.micronaut.http.HttpResponse;
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
    public HttpResponse<String> postSignup(@Body UsersDTO usersDto){
        Random random=new Random();
        usersDto.setId(random.nextInt(10000));
        userSignupService.signup(usersDto);
        return HttpResponse.ok("User Signup Successful");
    }

}
