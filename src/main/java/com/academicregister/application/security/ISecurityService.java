package com.academicregister.application.security;

public interface ISecurityService {

    String getJWTToken(String username);
    String encryptText(String text);
    Boolean isValidLogin(String username, String password);
    Boolean isAuthorized(String resource, String username);
}
