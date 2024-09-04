package com.platform.service;

import com.platform.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getById(UUID userId);

    User createUser(User user);

    List<User> getUsers();

    List<User> getUserByEmail(String email);


    User updateUser(UUID userId, User user);

    void deleteUser(UUID userId);

    boolean updateCredential(String email, String password);
}
