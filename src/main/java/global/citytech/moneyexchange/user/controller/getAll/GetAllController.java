package global.citytech.moneyexchange.user.controller.getAll;

import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.service.getall.GetAllService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/user")
public class GetAllController {
    private GetAllService getAllService;

    @Inject
    public GetAllController(GetAllService getAllService){
        this.getAllService=getAllService;
    }

    @Get("/get/{id}")
    public List<Users> getAllUsers(@PathVariable int id) {
        return (List<Users>) getAllService.getAllUsers(id);
    }
}
