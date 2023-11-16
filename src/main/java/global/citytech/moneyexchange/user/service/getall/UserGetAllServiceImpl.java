package global.citytech.moneyexchange.user.service.getall;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.security.ContextHolder;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserGetAllServiceImpl implements UserGetAllService {

    private final UserRepository userRepository;

    @Inject
    public UserGetAllServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserGetAllResponse> getAllUsers() {

        System.out.println("......" + ContextHolder.get());
        Optional<Users> userDto = userRepository.findById(ContextHolder.get().userID());

        if (userDto.get().getCheckStatus() == "PENDING")
            throw new CustomException(400, "User Status not Verified");

        if ("ADMIN".equalsIgnoreCase(ContextHolder.get().userRole())) {
            List<UserGetAllResponse> responses = new ArrayList<>();
            List<Users> allUsers = userRepository.findAll();

            for (Users user : allUsers) {
                UserGetAllResponse userResponse = convertToUserGetAllResponse(user);
                responses.add(userResponse);
            }
            if (allUsers.isEmpty()) {
                throw new CustomException(400, "No users found");
            }
            return responses;
        }

        if ("LENDER".equalsIgnoreCase(ContextHolder.get().userRole())) {
            List<UserGetAllResponse> responses = new ArrayList<>();
            List<Users> borrower = userRepository.findByUserRole(StatusAndRoleEnum.BORROWER);

            for (Users user : borrower) {
                UserGetAllResponse userResponse = convertToUserGetAllResponse(user);
                responses.add(userResponse);
            }
            return responses;

        }
        if ("BORROWER".equalsIgnoreCase(ContextHolder.get().userRole())) {
            List<UserGetAllResponse> responses = new ArrayList<>();
            List<Users> lender = userRepository.findByUserRole(StatusAndRoleEnum.LENDER);

            for (Users user : lender) {
                UserGetAllResponse userResponse = convertToUserGetAllResponse(user);
                responses.add(userResponse);
            }
            return responses;
        }
        else throw new CustomException(400,"User not found");
    }

    public UserGetAllResponse convertToUserGetAllResponse(Users user) {
        UserGetAllResponse userResponse = new UserGetAllResponse();

        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setUserName(user.getUserName());
        userResponse.setCitizenship(user.getCitizenship());
        userResponse.setUserRoleEnum(user.getUserRole());
        userResponse.setCheckStatus(user.getCheckStatus());
        userResponse.setCheckBlacklist(user.getCheckBlacklist());
        userResponse.setAvailableBalance(user.getAvailableBalance());

        return userResponse;
    }


}
