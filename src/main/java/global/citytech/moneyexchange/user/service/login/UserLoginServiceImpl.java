package global.citytech.moneyexchange.user.service.login;

import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.security.SecurityUtils;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.repository.UserRepository;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;

import java.util.Optional;

public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final SecurityUtils securityUtils;

    @Inject
    public UserLoginServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository,
                                SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Optional<UserLoginResponse> loginUser(UserLoginRequest request) {
        request.setPassword(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()));
        Optional<Users> usersList = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if(usersList.isPresent()){
            String userStatus = usersList.get().getCheckStatus();
            if(userStatus.equals("VERIFIED")){

                String token = securityUtils.token(usersList.get().getId(),usersList.get().getEmail(),
                        usersList.get().getUserRole().toString());

                return Optional.of(new UserLoginResponse(usersList.get().getUserName(),
                        usersList.get().getUserRole().name(),"User Logged in Successfully",token));

            }else{
                throw new CustomException(400,"User Status Not Verified");
            }
        }else{
            throw new CustomException(400,"User not found. Check Username or password");
        }
    }

}


//    public void sendEmail(UserLoginRequest request){
//
//    }

