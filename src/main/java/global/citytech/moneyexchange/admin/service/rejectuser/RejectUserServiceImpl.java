package global.citytech.moneyexchange.admin.service.rejectuser;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.security.ContextHolder;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;
import java.util.Optional;

public class RejectUserServiceImpl implements RejectUserService{
    private final UserRepository userRepository;

    @Inject
    public RejectUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RejectUserResponse> rejectUser(RejectUserRequest rejectUserRequest) {

        System.out.println("......" + ContextHolder.get());
        Optional<Users> userList = userRepository.findById(rejectUserRequest.getUserId());

        if (userList.isPresent()) {
            if ("ADMIN".equalsIgnoreCase(ContextHolder.get().userRole())) {

                this.rejectUser(rejectUserRequest.getUserId());

                return Optional.of(new RejectUserResponse(userList.get().getFirstName(),userList.get().getLastName(),
                        userList.get().getUserName(),userList.get().getEmail(),userList.get().getCitizenship(),
                        userList.get().getUserRole().name(),StatusAndRoleEnum.REJECTED.name(),
                        StatusAndRoleEnum.REJECTED.name(),"User Rejected successfully"));
            } else {
                throw new CustomException(400,"Only Admin User can verify the user details");
            }
        }
        throw new CustomException(400,"Invalid Admin UserName or Password");
    }

    private Users rejectUser(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus(StatusAndRoleEnum.REJECTED.name());
            user.get().setCheckBlacklist(StatusAndRoleEnum.REJECTED.name());
            return userRepository.update(user.get());
        }
        throw  new CustomException(400,"User not found");
    }




}
