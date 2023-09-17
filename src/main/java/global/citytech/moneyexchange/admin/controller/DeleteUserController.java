package global.citytech.moneyexchange.admin.controller;

import global.citytech.moneyexchange.admin.service.DeleteUserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;

@Controller("/admin")
public class DeleteUserController {
    private DeleteUserService deleteUserService;

    @Inject
    public DeleteUserController(DeleteUserService deleteUserService){
        this.deleteUserService = deleteUserService;
    }

    @Get("/delete/{id}")
    public HttpResponse<String> deleteUser(@PathVariable int id){
        deleteUserService.deleteUsers(id);
        return HttpResponse.ok("Successfully Deleted User");
    }
}
