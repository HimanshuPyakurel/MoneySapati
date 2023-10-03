package global.citytech.moneyexchange.transaction.controller.listdetails;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.transaction.service.listdetails.ListDetailsRequest;
import global.citytech.moneyexchange.transaction.service.listdetails.ListDetailsService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/user")
public class ListDetailsController {

    private final ListDetailsService listDetailsService;

    @Inject
    public ListDetailsController(ListDetailsService listDetailsService) {
        this.listDetailsService = listDetailsService;
    }

    @Post("/list")
    public List listUser(@Body ListDetailsRequest request){
        return listDetailsService.listDetails(request);
    }

}
