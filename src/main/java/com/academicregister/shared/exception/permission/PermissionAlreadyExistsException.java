package com.academicregister.shared.exception.permission;

public class PermissionAlreadyExistsException extends RuntimeException {
    public PermissionAlreadyExistsException(String role, String resource) {
        super(String.format("The permission with role %s and resource %s already exists.", role, resource));
    }
}
