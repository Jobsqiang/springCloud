package com.qiangjiang.provider.service.impl;


import com.qiangjiang.provider.dao.UserDao;
import com.qiangjiang.provider.model.User;
import com.qiangjiang.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User add(User user) {
        userDao.add(user);
        return user;
    }
}
