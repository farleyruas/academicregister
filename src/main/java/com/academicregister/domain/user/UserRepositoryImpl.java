package com.academicregister.domain.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements IUserRepository{

    private final JdbcTemplate template;

    public UserRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(User user) {
        template.update("INSERT INTO USERS VALUES (?, ?, ?, ?)",
                user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public void delete(String id) {
        var query = "DELETE FROM USERS WHERE ID = ?";
        template.update(query, id);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        var query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        var result = template.query(query, new UserMapper(), username, password);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public User findById(String id){
        var query = "SELECT * FROM USERS WHERE ID = ?";
        var result = template.query(query, new UserMapper(), id);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public User findByUsername(String username){
        var query = "SELECT * FROM USERS WHERE USERNAME = ?";
        var result = template.query(query, new UserMapper(), username);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
