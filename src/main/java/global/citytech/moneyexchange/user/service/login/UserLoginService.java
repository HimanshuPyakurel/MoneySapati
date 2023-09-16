package global.citytech.moneyexchange.user.service.login;

import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.model.Users;
import io.micronaut.http.HttpResponse;

import java.util.Optional;

public interface UserLoginService {

      HttpResponse<String> loginUser(UsersDTO usersDTO);

}
