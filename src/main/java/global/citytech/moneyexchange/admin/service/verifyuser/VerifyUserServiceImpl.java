package global.citytech.moneyexchange.admin.service.verifyuser;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.response.CustomResponse;
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
            if ("ADMIN".equalsIgnoreCase(userList.get().getUserRole().name())) {
                this.validateRequest(verifyUserRequest.getUserId());
                return new CustomResponse("User status update successfully",true);
            } else {
                throw new CustomException(400,"Only Admin User can verify the user details");
            }
        }
        throw new CustomException(400,"Invalid Admin UserName or Password");
    }

    private CustomResponse validateRequest(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus(StatusAndRoleEnum.VERIFIED.name());
            user.get().setCheckBlacklist(StatusAndRoleEnum.VERIFIED.name());
            userRepository.update(user.get());
            return new CustomResponse("User Successfully Validated",true);
        }
        throw  new CustomException(400,"User not found");
    }
}
