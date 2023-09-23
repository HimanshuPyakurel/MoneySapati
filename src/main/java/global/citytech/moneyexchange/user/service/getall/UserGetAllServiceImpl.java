package global.citytech.moneyexchange.user.service.getall;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserGetAllServiceImpl implements UserGetAllService {

    private final UserRepository userRepository;

    @Inject
    public UserGetAllServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers(UserGetAllRequest request) {
        Optional<Users> userDto = userRepository.findById(request.getUserId());

        if (userDto.isPresent()) {
            Users users = userDto.get();

            if ("Admin".equalsIgnoreCase(users.getUserRole().toString())) {
                List<Users> allUsers = userRepository.findAll();
                if (allUsers.isEmpty()) {
                    throw new CustomException("No users found");
                }
                return allUsers;
            }
            else if(users.getCheckStatus().equals("Pending")){
                throw new CustomException("User Status not Verified");
            }
            else if ("Lender".equalsIgnoreCase(users.getUserRole().toString())) {
                return userRepository.findByUserRole(StatusAndRoleEnum.BORROWER);
            } else {
                return userRepository.findByUserRole(StatusAndRoleEnum.LENDER);
            }
        }
        throw new CustomException("User not found");
    }

}
