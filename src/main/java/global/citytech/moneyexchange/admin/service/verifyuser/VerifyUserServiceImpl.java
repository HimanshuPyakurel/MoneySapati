package global.citytech.moneyexchange.admin.service.verifyuser;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.platform.security.ContextHolder;
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
    public Optional<VerifyUserResponse> verifyUser(VerifyUserRequest verifyUserRequest) {

        System.out.println("......" + ContextHolder.get());
        Optional<Users> userList =  userRepository.findById(verifyUserRequest.getUserId());

        if(userList.isPresent() ) {
            if ("ADMIN".equalsIgnoreCase(ContextHolder.get().userRole())) {
                this.validateRequest(verifyUserRequest.getUserId());
                return Optional.of(new VerifyUserResponse(userList.get().getFirstName(),userList.get().getLastName(),
                        userList.get().getUserName(),userList.get().getEmail(),userList.get().getCitizenship(),
                        userList.get().getUserRole().name(),StatusAndRoleEnum.VERIFIED.name(),
                        StatusAndRoleEnum.VERIFIED.name(),"User Verified successfully"));
            } else {
                throw new CustomException(400,"Only Admin User can verify the user details");
            }
        }
        throw new CustomException(400,"Invalid Admin UserName or Password");
    }

    private Users validateRequest(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus(StatusAndRoleEnum.VERIFIED.name());
            user.get().setCheckBlacklist(StatusAndRoleEnum.VERIFIED.name());
            return userRepository.update(user.get());
        }
        throw  new CustomException(400,"User not found");
    }
}
