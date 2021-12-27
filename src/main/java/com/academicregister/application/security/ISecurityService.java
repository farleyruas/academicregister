package com.academicregister.application.security;

import java.security.NoSuchAlgorithmException;

public interface ISecurityService {

    String getJWTToken(String username);
    String encryptText(String text) throws NoSuchAlgorithmException;
    Boolean isValidLogin(String username, String password);
}
