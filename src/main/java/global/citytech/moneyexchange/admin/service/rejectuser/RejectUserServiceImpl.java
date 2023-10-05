package global.citytech.moneyexchange.admin.service.rejectuser;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;

import java.util.Optional;

public class RejectUserServiceImpl implements RejectUserService{
    private final UserRepository userRepository;

    @Inject
    public RejectUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CustomResponse rejectUser(RejectUserRequest rejectUserRequest) {

        Optional<Users> userList = userRepository.findByEmail(rejectUserRequest.getAdminEmail());
        String checkPassword = DigestUtils.md5DigestAsHex(rejectUserRequest.getAdminPassword().getBytes());

        if (userList.isPresent() && userList.get().getPassword().equals(checkPassword)) {
            if (userList.get().getUserRole() == StatusAndRoleEnum.ADMIN) {
                this.validateRequest(rejectUserRequest.getUserId());
                return new CustomResponse("User rejected",true);
            } else {
                throw new CustomException(400,"Only Admin User can verify the user details");
            }
        }
        throw new CustomException(400,"Invalid Admin UserName or Password");
    }

    private CustomResponse validateRequest(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus("Rejected");
            user.get().setCheckBlacklist("Rejected");
            userRepository.update(user.get());
            return new CustomResponse("User Successfully Validated",true);
        }
        throw  new CustomException(400,"User not found");
    }




}
