package global.citytech.moneyexchange.admin.service.verifyuser;

import global.citytech.moneyexchange.response.CustomResponse;

public interface VerifyUserService {
    CustomResponse verifyUser(VerifyUserRequest verifyUserRequest);
}
