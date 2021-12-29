package com.academicregister.domain.user;

public interface IUserRepository {
    void save(User user);
    void delete(String id);
    User findById(String id);
    User findUserByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
