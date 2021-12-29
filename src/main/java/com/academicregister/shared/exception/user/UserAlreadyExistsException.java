package com.academicregister.shared.exception.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String id){
        super(String.format("The user %s already exists.", id));
    }
}
