package global.citytech.moneyexchange.admin.service.verifyuser;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;
import java.util.Optional;

public class VerifyUserServiceImpl implements VerifyUserService {

    private final UserRepository userRepository;

    @Inject
    public VerifyUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CustomResponse verifyUser(VerifyUserRequest verifyUserRequest) {

        Optional<Users> userList=  userRepository.findByEmail(verifyUserRequest.getAdminEmail());
        String checkPassword = DigestUtils.md5DigestAsHex(verifyUserRequest.getAdminPassword().getBytes());

        if(userList.isPresent() && userList.get().getPassword().equals(checkPassword)) {
            if (userList.get().getUserRole() == StatusAndRoleEnum.ADMIN) {
                this.validateRequest(verifyUserRequest.getUserId());
                return new CustomResponse("User status update successfully",true);
            } else {
                throw new CustomException("Only Admin User can verify the user details");
            }
        }
        throw new CustomException("Invalid Admin UserName or Password");
    }

    public CustomResponse validateRequest(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus("Verified");
            user.get().setCheckBlacklist("Verified");
            userRepository.update(user.get());
            return new CustomResponse("User Successfully Validated",true);
        }
        throw  new CustomException("User not found");
    }
}
