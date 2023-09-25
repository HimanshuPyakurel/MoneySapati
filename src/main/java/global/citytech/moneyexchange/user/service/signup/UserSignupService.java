package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.dto.UsersDTO;

public interface UserSignupService {

    CustomResponse signup(UsersDTO usersDto);
}
