package global.citytech.moneyexchange.admin.service.impl;

import global.citytech.moneyexchange.admin.service.VerifyUserService;
import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class VerifyUserServiceImpl implements VerifyUserService {

    private final UserRepository userRepository;

    @Inject
    public VerifyUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Users verifyUser(int id) {
        this.validateRequest(id);
        return userRepository.findById(id).get();
    }
    public void validateRequest(int id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setCheckStatus("Verified");
            user.get().setCheckBlacklist("Verified");
            userRepository.update(user.get());
        }
    }
}
