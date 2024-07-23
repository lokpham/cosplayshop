package com.cosplaystore.cosplaystore.serviceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

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
}
