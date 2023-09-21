package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;
import java.util.List;
import java.util.Optional;

public class UserSignupServiceImpl implements UserSignupService {

    private final UserRepository userRepository;

    @Inject
    public UserSignupServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users signup(UsersDTO usersDto) {
        Users users = new Users();

        this.validateRequest(usersDto);
        this.checkAdminForStatus(usersDto);

        users.setCheckStatus(usersDto.getCheckStatus());
        users.setCheckBlacklist(usersDto.getCheckBlacklist());
        users.setPassword(DigestUtils.md5DigestAsHex(usersDto.getPassword().getBytes()));
        users.setUserName(usersDto.getUserName());
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setEmail(usersDto.getEmail());
        users.setCitizenship(usersDto.getCitizenship());
        users.setUserRole(usersDto.getUserRole());

        this.availableBalanceForLenderAndBorrower(usersDto);
        return this.userRepository.save(users);
    }

    public void validateRequest(UsersDTO userDto) {
        Optional<Users> existingUserName = this.userRepository.findByEmail(userDto.getEmail());
        List<Users> existingAdminRole = this.userRepository.findByUserRole(userDto.getUserRole());

        if (existingUserName.isPresent()) {
            throw new CustomException("User Email already exists");
        }
        if ("Admin".equalsIgnoreCase(existingAdminRole.get(0).getUserRole().name())){
            throw new CustomException("Admin already exists");
        }
    }
    public void checkAdminForStatus(UsersDTO usersDTO){
        if ("ADMIN".equalsIgnoreCase(usersDTO.getUserRole().toString())){
            usersDTO.setCheckStatus("Verified");
            usersDTO.setCheckBlacklist("Verified");
        }
        else {
            usersDTO.setCheckStatus("Pending");
            usersDTO.setCheckBlacklist("Pending");
        }
    }

    public void availableBalanceForLenderAndBorrower(UsersDTO usersDTO){
        Users users =new Users();
        if("LENDER".equalsIgnoreCase(usersDTO.getUserRole().name())){
            users.setAvailableBalance(usersDTO.getAvailableBalance());
        }
        else users.setAvailableBalance(0);
    }


}


