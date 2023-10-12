package global.citytech.moneyexchange.admin.service.rejectuser;

import global.citytech.moneyexchange.platform.response.CustomResponse;

import java.util.Optional;

public interface RejectUserService {
    Optional<RejectUserResponse> rejectUser(RejectUserRequest rejectUserRequest);
}
