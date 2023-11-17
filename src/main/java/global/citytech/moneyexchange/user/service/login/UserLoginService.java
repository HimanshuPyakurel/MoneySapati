package global.citytech.moneyexchange.user.service.login;

import java.util.Optional;

public interface UserLoginService {

      Optional<UserLoginResponse> loginUser(UserLoginRequest request);

}
