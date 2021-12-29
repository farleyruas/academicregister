package com.academicregister.domain.rol;

import java.util.List;

public interface IRoleRepository {
    void save(Role role);
    Role findById(String id);
    Role findByName(String name);
    List<Role> findAll();
}
