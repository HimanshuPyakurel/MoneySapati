package global.citytech.moneyexchange.user.service.login.impl;

import global.citytech.moneyexchange.user.service.login.UserLoginService;
import global.citytech.moneyexchange.user.model.Users;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.repository.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import org.springframework.util.DigestUtils;
import java.util.Optional;

public class UserLoginServiceImpl implements UserLoginService {

    private UserRepository userRepository;

    @Inject
    public UserLoginServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public HttpResponse<String> loginUser(UsersDTO usersDTO) {
        usersDTO.setPassword(DigestUtils.md5DigestAsHex(usersDTO.getPassword().getBytes()));
        Optional<Users> usersList = userRepository.findByEmailAndPassword(usersDTO.getEmail(), usersDTO.getPassword());
        if(usersList.isPresent()){
            String userStatus = usersList.get().getCheckStatus();
            if(userStatus.equals("Verified")){
                return HttpResponse.status(HttpStatus.ACCEPTED).body(usersList.get().getUserRole()+ " User Logged in Successfully");
            }else{
               return HttpResponse.status(HttpStatus.ACCEPTED).body("User Status not Verified");
            }
        }else{
            return HttpResponse.status(HttpStatus.ACCEPTED).body("User not found. Check Username or password.");
        }
    }

//    public String generateTokens(){
//
//    }=

}
