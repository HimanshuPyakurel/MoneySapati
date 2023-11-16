package global.citytech.moneyexchange.user.service.getall;

import global.citytech.moneyexchange.user.repository.Users;

import java.util.List;
import java.util.Optional;

public interface UserGetAllService {
    List<UserGetAllResponse> getAllUsers();

}
