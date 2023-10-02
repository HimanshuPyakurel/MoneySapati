package global.citytech.moneyexchange.user.service.login;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.Users;
import global.citytech.moneyexchange.user.dto.UsersDTO;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.utils.EmailUtils;
import jakarta.inject.Inject;
import jdk.jshell.Snippet;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserLoginServiceImpl implements UserLoginService {

    private UserRepository userRepository;

    @Inject
    public UserLoginServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public CustomResponse loginUser(UsersDTO usersDTO) {
        usersDTO.setPassword(DigestUtils.md5DigestAsHex(usersDTO.getPassword().getBytes()));
        Optional<Users> usersList = userRepository.findByEmailAndPassword(usersDTO.getEmail(), usersDTO.getPassword());
        if(usersList.isPresent()){
            String userStatus = usersList.get().getCheckStatus();
            if(userStatus.equals("Verified")){
//                this.sendEmail(UsersDTO userDto);

                return new CustomResponse(" User Logged in Successfully",true);
            }else{
                throw new CustomException("User Status not Verified");
            }
        }else{
            throw new CustomException("User not found. Check Username or password");
        }
    }

//    public void sendEmail(UsersDTO usersDTO){
//
//    }


}
