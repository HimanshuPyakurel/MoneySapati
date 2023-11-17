//package global.citytech.moneyexchange.user.service.signup;
//
//import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
//import global.citytech.moneyexchange.user.repository.UserRepository;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//@MicronautTest
//public class UserSignupServiceImplTest {
//    private UserRepository userRepository;
//    private UserSignupServiceImpl userSignupServiceImpl;
//
//    @BeforeEach
//    void setUp(){
//        userRepository = Mockito.mock(UserRepository.class);
//        userSignupServiceImpl = new UserSignupServiceImpl(userRepository);
//    }
//
//    @Test
//    void shouldReturnSuccessIfAllDetailSuccess(){
//        UserSignupRequest request = new UserSignupRequest(
//                "java",
//                "program",
//                "java",
//                "java@gmail.com",
//                "Aa1235das@",
//                "Nepali",
//                StatusAndRoleEnum.LENDER,
//                3000);
//
//        var result = userSignupServiceImpl.signup(request);
//        assertEquals("Successfully Signup", result.get());
//    }
//
//    @Test
//    void shouldReturnFailIfAllEmailFormatIncorrect(){
//        UserSignupRequest request = new UserSignupRequest(
//                "java",
//                "program",
//                "java",
//                "java.com",
//                "Aa1235das@",
//                "Nepali",
//                StatusAndRoleEnum.LENDER,
//                3000);
//
//        try{
//            userSignupServiceImpl.signup(request);
//        }catch (Exception e) {
//            assertEquals("Invalid email format", e.getMessage());
//        }
//    }
//
//    @Test
//    void shouldReturnFailIfAllPasswordFormatIncorrect(){
//        UserSignupRequest request = new UserSignupRequest(
//                "java",
//                "program",
//                "java",
//                "java@gmail.com",
//                "",
//                "Nepali",
//                StatusAndRoleEnum.LENDER,
//                3000);
//
//        try{
//            userSignupServiceImpl.signup(request);
//        }catch (Exception e) {
//            assertEquals("Invalid password format", e.getMessage());
//        }
//    }
//
//
//}
