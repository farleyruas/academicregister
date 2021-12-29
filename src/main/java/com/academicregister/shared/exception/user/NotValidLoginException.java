package com.academicregister.shared.exception.user;

public class NotValidLoginException extends RuntimeException{
    public NotValidLoginException(){
        super(String.format("The credentials provided were invalid."));
    }
}
