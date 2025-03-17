package com.machinecode.mbs.controllers;

import com.machinecode.mbs.dtos.*;
import com.machinecode.mbs.exceptions.UserNotFoundException;
import com.machinecode.mbs.models.User;
import com.machinecode.mbs.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
//take in the request dto and return the response dto
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserSignUpResponseDto signUp(UserSignUpRequestDto userSignUpRequestDto){
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        try{
            User user = userService.signUp(userSignUpRequestDto.getName(), userSignUpRequestDto.getEmail(), userSignUpRequestDto.getPassword());
            userSignUpResponseDto.setUserId(user.getId());
            userSignUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            userSignUpResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return userSignUpResponseDto;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if(user != null){
            loginResponseDto.setLoginStatus(LoginResponseStatus.SUCCESS);
        } else {
            loginResponseDto.setLoginStatus(LoginResponseStatus.FAILURE);
        }
        return loginResponseDto;
    }
}
