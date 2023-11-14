package global.citytech.moneyexchange.user.controller.signup;

import global.citytech.moneyexchange.platform.response.RestResponse;
import global.citytech.moneyexchange.user.service.signup.UserSignupRequest;
import global.citytech.moneyexchange.user.service.signup.UserSignupResponse;
import global.citytech.moneyexchange.user.service.signup.UserSignupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user")
public class UserSignupController {
    private final UserSignupService userSignupService;

    @Inject
    public UserSignupController(UserSignupService userSignupService) {
        this.userSignupService = userSignupService;
    }

    @Post("/signup")
    public HttpResponse<RestResponse> signupUser(@Body UserSignupRequest request) {
        var response = userSignupService.signup(request);
        return HttpResponse.ok(RestResponse.success(response.get()));
    }

}
