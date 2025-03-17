package com.machinecode.mbs;

import com.machinecode.mbs.controllers.UserController;
import com.machinecode.mbs.dtos.*;
import com.machinecode.mbs.exceptions.UserNotFoundException;
import com.machinecode.mbs.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MbsApplicationTests {


    @Autowired
    private UserController userController;
    @Test
    void contextLoads() {
    }

//    @Test
//    public void testSignUp() {
//        UserSignUpRequestDto userSignUpRequestDto = new UserSignUpRequestDto();
//        userSignUpRequestDto.setName("Eric Cartman");
//        userSignUpRequestDto.setEmail("EricCartman@skrewU.com");
//        userSignUpRequestDto.setPassword("beefcake");
//        UserSignUpResponseDto userSignUpResponseDto = userController.signUp(userSignUpRequestDto);
//        System.out.println(userSignUpResponseDto.getUserId());
//        System.out.println("sdkfjbduf ");
//    }
@Test
public void testLogin() throws UserNotFoundException {
    LoginRequestDto loginRequestDto = new LoginRequestDto();
//    loginRequestDto.setEmail("Eric Cartman").;
    loginRequestDto.setEmail("EricCartman@skrewU.com");
    loginRequestDto.setPassword("beefcae");
    LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
    if(loginResponseDto.getLoginStatus()== LoginResponseStatus.SUCCESS){
        System.out.println("Login Successful");
    } else {
        System.out.println("Login Failed");
    }
//    System.out.println(loginResponseDto.getUserId());
//    System.out.println("sdkfjbduf ");
}

}
