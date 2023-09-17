package global.citytech.moneyexchange.admin.service.impl;

import global.citytech.moneyexchange.admin.service.DeleteUserService;
import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;

    @Inject
    public DeleteUserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void deleteUsers(int id) {
        Optional<Users> userToDelete = userRepository.findById(id);
        if ("ADMIN".equalsIgnoreCase(userToDelete.get().getUserRole().toString())){
            throw new IllegalArgumentException("Admin user cannot be deleted.");
        }
        else if(userToDelete.isPresent()){
            userRepository.deleteById(id);
        }
        else throw new IllegalArgumentException("User's id not found");

    }
}
