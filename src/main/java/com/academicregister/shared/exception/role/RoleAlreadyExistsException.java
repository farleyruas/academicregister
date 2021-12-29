package com.academicregister.shared.exception.role;

public class RoleAlreadyExistsException extends RuntimeException{
    public RoleAlreadyExistsException(String text) {
        super(String.format("The role %s already exists.", text));
    }
}
