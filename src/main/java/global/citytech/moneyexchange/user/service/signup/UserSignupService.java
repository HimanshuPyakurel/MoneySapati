package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.platform.response.CustomResponse;

import java.util.Optional;

public interface UserSignupService {

    Optional<UserSignupResponse> signup(UserSignupRequest request);
}
