package global.citytech.moneyexchange.admin.controller;

import global.citytech.moneyexchange.admin.service.AdminService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/admin")
public class AdminController {
    private AdminService adminService;

    @Inject
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @Put("/verify/{id}")
    public HttpResponse<String> verifyUsers(@PathVariable int id){
        adminService.verifyUser(id);
        return HttpResponse.ok("Verified User Successfully");
    }



}
