package com.syf.blog1.service;

import com.syf.blog1.dao.UserRepository;
import com.syf.blog1.po.User;
import com.syf.blog1.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//查询信息

@Service
public class UserServicelmpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUsser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));


        return user;
    }
}
