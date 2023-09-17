package global.citytech.moneyexchange.admin.controller;

import global.citytech.moneyexchange.admin.service.VerifyUserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/admin")
public class VerifyUsersController {
    private VerifyUserService verifyUserService;

    @Inject
    public VerifyUsersController(VerifyUserService verifyUserService){
        this.verifyUserService = verifyUserService;
    }

    @Put("/verify/{id}")
    public HttpResponse<String> verifyUsers(@PathVariable int id){
        verifyUserService.verifyUser(id);
        return HttpResponse.ok("Verified User Successfully");
    }



}
