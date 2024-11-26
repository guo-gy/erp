package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.model.User;
import com.erp.repository.UserRepository;

@Service
public class UserServer {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User saveadmin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAllPermissions();
        return userRepository.save(user);
    }

    public User saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNullPermissions();
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
