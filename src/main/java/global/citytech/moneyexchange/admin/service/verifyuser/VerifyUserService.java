package global.citytech.moneyexchange.admin.service.verifyuser;

import global.citytech.moneyexchange.platform.response.CustomResponse;

import java.util.Optional;

public interface VerifyUserService {
    Optional<VerifyUserResponse> verifyUser(VerifyUserRequest verifyUserRequest);
}
