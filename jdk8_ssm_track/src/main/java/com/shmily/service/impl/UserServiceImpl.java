package com.shmily.service.impl;

import com.shmily.mapper.UserMapper;
import com.shmily.model.User;
import com.shmily.service.UserService;
import com.shmily.util.DateUtils;
import com.shmily.util.DesUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * Created by Administrator on 2016/12/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int selectByNameAndPsd(String name, String psd) {
        psd = DesUtil.encode(psd);
        return userMapper.selectByNameAndPsd(name,psd);
    }


}
