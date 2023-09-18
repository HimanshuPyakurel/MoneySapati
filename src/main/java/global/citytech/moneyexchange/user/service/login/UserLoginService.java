package global.citytech.moneyexchange.user.service.login;

import global.citytech.moneyexchange.user.dto.UsersDTO;
import io.micronaut.http.HttpResponse;

public interface UserLoginService {

      HttpResponse<String> loginUser(UsersDTO usersDTO);

}
