package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.dto.UsersDTO;

public interface UserSignupService {

    Users signup(UsersDTO usersDto);
}
