package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.User;
import com.erp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 增
    public User addUser(String userName, String password, Integer companyId) {
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        user.setCompanyId(companyId);
        userRepository.save(user);
        return user;
    }

    // 删
    public void delUser(Integer id) {
        userRepository.deleteById(id);
    }

    // 改
    public User updUser(Integer id, String name, String description) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setId(id);
            user.setName(name);
            user.setDescription(description);
            userRepository.save(user);
        }
        return user;

    }

    // 查
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserByCompanyId(Integer companyId) {
        return userRepository.findByCompanyId(companyId);
    }

}
