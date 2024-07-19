package com.vorobey.userservice.service;

import com.vorobey.userservice.entity.user.User;

public interface UserService {
    User registerUser(User user);
    User findUserById(Long id);
    User findUserByUsername(String username);
    User updateUser(User user);
    void deleteUserById(Long id);
}
