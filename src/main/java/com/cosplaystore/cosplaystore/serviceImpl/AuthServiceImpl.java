package com.cosplaystore.cosplaystore.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.authentication.JwtService;
import com.cosplaystore.cosplaystore.dto.request.LoginRequest;
import com.cosplaystore.cosplaystore.dto.request.UserRegisterRequest;
import com.cosplaystore.cosplaystore.dto.response.AuthReponse;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.dto.response.UserResponse;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.UserMapper;
import com.cosplaystore.cosplaystore.model.Role;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.repository.UserRepo;
import com.cosplaystore.cosplaystore.service.AuthService;
import com.cosplaystore.cosplaystore.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        String password = userRegisterRequest.getPassword();
        String password_hash = passwordEncoder.encode(password);

        User user = userMapper.toUser(userRegisterRequest);
        user.setBirth_day(LocalDate.parse(userRegisterRequest.getBirth_day(), DATE_FORMATTER));
        user.setPassword_hash(password_hash);
        user.setRole(Role.USER);
        user.setDisable(false);

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public AuthReponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());

        if (user.isPresent()) {
            String token = jwtService.generateToken(user.get());

            AuthReponse authReponse = new AuthReponse();
            authReponse.setAccessToken(token);
            authReponse.setEmail(user.get().getEmail());
            return authReponse;
        } else {
            throw new GeneralException(Message.USERNAME_NOTFOUND);
        }

    }

}
