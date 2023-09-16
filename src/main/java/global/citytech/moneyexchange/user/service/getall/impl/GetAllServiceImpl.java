package global.citytech.moneyexchange.user.service.getall.impl;

import global.citytech.moneyexchange.user.model.UserRoleEnum;
import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.service.getall.GetAllService;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GetAllServiceImpl implements GetAllService {

    private final UserRepository userRepository;

    @Inject
    public GetAllServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers(int id) {
        Optional<Users> userDto = userRepository.findById(id);

        if (userDto.isPresent()) {
            Users users = userDto.get();

            if ("Admin".equalsIgnoreCase(users.getUserRole().toString())) {
                List<Users> allUsers = userRepository.findAll();
                if (allUsers.isEmpty()) {
                    throw new IllegalArgumentException("No users found");
                }
                return allUsers;

            } else if ("Lender".equalsIgnoreCase(users.getUserRole().toString())) {
                return userRepository.findByUserRole(UserRoleEnum.BORROWER);

            } else {
                return userRepository.findByUserRole(UserRoleEnum.LENDER);
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
