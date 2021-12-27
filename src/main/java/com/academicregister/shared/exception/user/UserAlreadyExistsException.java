package com.academicregister.shared.exception.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String id){
        super(String.format("The user id %s already exists.", id));
    }
}
