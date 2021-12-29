package com.academicregister.application.role;

import com.academicregister.domain.rol.Role;

import java.util.List;

public interface IRoleService {
    String createRole(Role role);
    List<Role> findAllRoles();
}
