package com.syf.blog1.dao;

import com.syf.blog1.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
//连接查询数据库

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);
}
