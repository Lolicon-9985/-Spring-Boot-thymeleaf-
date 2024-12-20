package com.syf.blog1.service;

import com.syf.blog1.po.User;

public interface UserService {
    User checkUsser(String username, String password);
}
