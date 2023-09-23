package global.citytech.moneyexchange.user.service.getall;

import global.citytech.moneyexchange.user.repository.Users;
import java.util.List;

public interface UserGetAllService {
    List<Users> getAllUsers(UserGetAllRequest request);

}
