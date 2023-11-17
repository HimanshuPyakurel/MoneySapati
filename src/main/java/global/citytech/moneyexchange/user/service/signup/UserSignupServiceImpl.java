package global.citytech.moneyexchange.user.service.signup;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignupServiceImpl implements UserSignupService {

    private final UserRepository userRepository;

    @Inject
    public UserSignupServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserSignupResponse> signup(UserSignupRequest request) {
        this.validateRequest(request);
        Users users = new Users();

        if ("ADMIN".equalsIgnoreCase(request.getUserRole().toString())){
            users.setCheckStatus(StatusAndRoleEnum.VERIFIED.name());
            users.setCheckBlacklist(StatusAndRoleEnum.VERIFIED.name());
        }
        else {
            users.setCheckStatus(StatusAndRoleEnum.PENDING.name());
            users.setCheckBlacklist(StatusAndRoleEnum.PENDING.name());
        }

        Random random=new Random();

        users.setId(random.nextInt(10000));
        users.setPassword(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()));
        users.setUserName(request.getUserName());
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setEmail(request.getEmail());
        users.setCitizenship(request.getCitizenship());
        users.setUserRole(request.getUserRole());

        if("LENDER".equalsIgnoreCase(request.getUserRole().name())){
            users.setAvailableBalance(request.getAvailableBalance());
        } else users.setAvailableBalance(0);

        this.userRepository.save(users);

        return Optional.of(new UserSignupResponse(users.getFirstName(),users.getLastName(), users.getUserName(),
                users.getEmail(), users.getCitizenship(), users.getUserRole().name(),"User Signup Successfully"));
    }

    private void validateRequest(UserSignupRequest request) {
        Optional<Users> existingUserName = this.userRepository.findByEmail(request.getEmail());

        if(!isValidEmail(request.getEmail())){
            throw new CustomException(400,"Invalid email format");
        }

        if(!isValidPassword(request.getPassword())){
            throw new CustomException(400,"Invalid password format");
        }

        if (existingUserName.isPresent()) {
            throw new CustomException(400,"User Email already exists");
        }

        if ("ADMIN".equalsIgnoreCase(request.getUserRole().name())){
            List<Users> rootAdminUser = this.userRepository.findByUserRole(StatusAndRoleEnum.ADMIN);

            if(!rootAdminUser.isEmpty()){
                throw new CustomException(400,"Admin already exists");
            }
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail)\\.(?:com)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }

}


