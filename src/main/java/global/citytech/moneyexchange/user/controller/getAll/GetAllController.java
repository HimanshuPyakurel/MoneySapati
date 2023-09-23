package global.citytech.moneyexchange.user.controller.getAll;

import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.service.getall.UserGetAllRequest;
import global.citytech.moneyexchange.user.service.getall.UserGetAllService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/user")
public class GetAllController {
    private UserGetAllService userGetAllService;

    @Inject
    public GetAllController(UserGetAllService userGetAllService) {
        this.userGetAllService = userGetAllService;
    }

    @Post("/get")
    public List<Users> getAllUsers(@Body UserGetAllRequest request) {
        return userGetAllService.getAllUsers(request);
    }
}
