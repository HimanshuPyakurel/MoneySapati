package global.citytech.moneyexchange.admin.service.rejectuser;

import global.citytech.moneyexchange.platform.response.CustomResponse;

public interface RejectUserService {
    CustomResponse rejectUser(RejectUserRequest rejectUserRequest);
}
