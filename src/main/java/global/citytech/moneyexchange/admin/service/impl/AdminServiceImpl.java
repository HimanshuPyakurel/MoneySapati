package global.citytech.moneyexchange.admin.service.impl;

import global.citytech.moneyexchange.admin.service.AdminService;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Inject
    public AdminServiceImpl(UserRepository userRepository){
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
