package com.academicregister.shared.exception.role;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String text) {
        super(String.format("The role %s was not found", text));
    }
}
