package com.academicregister.shared.exception.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String text){
        super(String.format("The user %s was not found", text));
    }
}
