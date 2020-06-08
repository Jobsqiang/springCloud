package com.qiangjiang.provider.dao;


import com.qiangjiang.provider.mapper.UserMapper;
import com.qiangjiang.provider.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public void add(User user){
        userMapper.insert(user);
    }
}
