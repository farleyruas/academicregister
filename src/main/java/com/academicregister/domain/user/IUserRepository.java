package com.academicregister.domain.user;

public interface IUserRepository {
    void save(User user);
    User findById(String id);
    User findUserByUsernameAndPassword(String username, String password);
}
