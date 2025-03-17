package com.machinecode.mbs.services;

import com.machinecode.mbs.dtos.LoginResponseDto;
import com.machinecode.mbs.dtos.LoginResponseStatus;
import com.machinecode.mbs.dtos.UserSignUpRequestDto;
import com.machinecode.mbs.exceptions.SeatNotAvailableException;
import com.machinecode.mbs.exceptions.ShowNotFoundException;
import com.machinecode.mbs.exceptions.UserNotFoundException;
import com.machinecode.mbs.models.User;
import com.machinecode.mbs.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public User signUp(String name, String email, String password) {
        //check if the user already exists through the email.
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User user = optionalUser.orElse(new User());
        if(optionalUser.isEmpty()){
            user = new User();
            user.setName(name);
            user.setEmail(email);
//            user.setPassword(password);
            //this shouldn't be done.
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user = userRepository.save(user);
        }

         return user;
    }
    public User login(String email, String password) throws UserNotFoundException {
        boolean ans = true;
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
//        System.out.println(ans);

        if(optionalUser.isPresent()){
            ans = true;
        }
        if(!ans){
            throw new UserNotFoundException("User with the email: "+email+" does not exist.");
        }
        User user = optionalUser.get();
//        System.out.println(ans);
        //check for the password.
        ans = ans & bCryptPasswordEncoder.matches(password, user.getPassword());
        System.out.println(ans);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        if(ans){
           return user;
        }
        return null;
    }
}

