package com.shmily.service;

import com.shmily.model.User;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public interface UserService {
    User getById(int id);

    int saveUser(User user);

    int selectByNameAndPsd(String nanme,String pad);
}
