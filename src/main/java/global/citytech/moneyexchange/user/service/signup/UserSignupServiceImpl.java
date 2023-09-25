package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.response.CustomResponse;
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
    public CustomResponse signup(UsersDTO usersDto) {
        this.validateRequest(usersDto);
        Users users = new Users();

        if ("ADMIN".equalsIgnoreCase(usersDto.getUserRole().toString())){
            users.setCheckStatus(StatusAndRoleEnum.VERIFIED.name());
            users.setCheckBlacklist(StatusAndRoleEnum.VERIFIED.name());
        }
        else {
            users.setCheckStatus(StatusAndRoleEnum.PENDING.name());
            users.setCheckBlacklist(StatusAndRoleEnum.PENDING.name());
        }

        users.setPassword(DigestUtils.md5DigestAsHex(usersDto.getPassword().getBytes()));
        users.setUserName(usersDto.getUserName());
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setEmail(usersDto.getEmail());
        users.setCitizenship(usersDto.getCitizenship());
        users.setUserRole(usersDto.getUserRole());

        if("LENDER".equalsIgnoreCase(usersDto.getUserRole().name())){
            users.setAvailableBalance(usersDto.getAvailableBalance());
        } else users.setAvailableBalance(0);


        this.userRepository.save(users);

        return new CustomResponse("Successfully Signup",true);
    }

    public void validateRequest(UsersDTO userDto) {

        Optional<Users> existingUserName = this.userRepository.findByEmail(userDto.getEmail());

        if (existingUserName.isPresent()) {
            throw new CustomException("User Email already exists");
        }

        if ("ADMIN".equalsIgnoreCase(userDto.getUserRole().name())){
            List<Users> rootAdminUser = this.userRepository.findByUserRole(StatusAndRoleEnum.ADMIN);

            if(!rootAdminUser.isEmpty()){
                throw new CustomException("Admin already exists");
            }
        }

    }

}


