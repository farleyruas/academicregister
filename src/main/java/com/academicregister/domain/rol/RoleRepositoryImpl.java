package com.academicregister.domain.rol;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements IRoleRepository{

    private final JdbcTemplate template;

    public RoleRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Role role) {
        template.update("INSERT INTO ROLES VALUES (?, ?)",
                role.getId(), role.getName());
    }

    @Override
    public Role findById(String id) {
        var query = "SELECT * FROM ROLES WHERE ID = ?";
        var result = template.query(query, new RoleMapper(), id);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Role findByName(String name) {
        var query = "SELECT * FROM ROLES WHERE NAME = ?";
        var result = template.query(query, new RoleMapper(), name);
        return result.get(0);
    }

    @Override
    public List<Role> findAll() {
        var query = "SELECT * FROM ROLES";
        var result = template.query(query, new RoleMapper());
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }
}
