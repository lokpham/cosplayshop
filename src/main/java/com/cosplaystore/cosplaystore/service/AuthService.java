package com.cosplaystore.cosplaystore.service;

import java.security.NoSuchAlgorithmException;

public interface AuthService {
    public String encodePassword(String password);

}
