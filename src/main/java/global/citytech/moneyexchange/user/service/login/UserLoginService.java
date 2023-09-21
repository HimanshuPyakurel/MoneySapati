package global.citytech.moneyexchange.user.service.login;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.user.dto.UsersDTO;

public interface UserLoginService {

      CustomResponse loginUser(UsersDTO usersDTO);

}
