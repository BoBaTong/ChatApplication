package com.student.services;

import com.student.DataStorage;
import com.student.repository.UserRepository;
import com.student.user.User;

public class UserServices {
    UserRepository userRepository = new UserRepository();
    MD5 md5 = new MD5();

    public boolean login(String username,String password)
    {
        User user =userRepository.queryUserByUsername(username);
        return DataStorage.getInstance().getUserCollection().contains(user);
    }

    public boolean checkHashPassword(String password)
    {
        return false;
    }
}
