package com.cosplaystore.cosplaystore.serviceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String encodePassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            // Return the encoded password
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        String password = userRegisterRequest.getPassword();
        String password_hash = encodePassword(password);

        User user = userMapper.toUser(userRegisterRequest);
        user.setBirth_day(LocalDate.parse(userRegisterRequest.getBirth_day(), DATE_FORMATTER));
        user.setPassword_hash(password_hash);
        user.setRole(Role.USER);
        user.setDisable(false);

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public AuthReponse login(LoginRequest loginRequest) {

        Optional<User> user_opt = userRepo.findByEmail(loginRequest.getEmail());

        if (user_opt.isPresent()) {

            User user = user_opt.get();

            if (user.getPassword_hash().equals(encodePassword(loginRequest.getPassword()))) {
                String token = jwtService.getJWTAccessToken(user);
                AuthReponse authReponse = new AuthReponse();
                authReponse.setAccessToken(token);
                authReponse.setEmail(user.getEmail());
                return authReponse;
            } else {
                throw new GeneralException(Message.WRONG_PASSWORD);
            }

        } else {
            throw new GeneralException(Message.USERNAME_NOTFOUND);
        }

    }

}
