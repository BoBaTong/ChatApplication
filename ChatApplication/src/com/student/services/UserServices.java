package com.student.services;

import com.student.DataStorage;
import com.student.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServices {
    DataStorage dataStorage = DataStorage.getInstance();

    public boolean addUser(String username, String password, String lastName, String firstName, String fullName, String gender, String dateOfBirth) {
        String hashedPassword = hashPassword(password);
        User tempUser = new User(lastName, firstName, fullName, username, hashedPassword, gender, dateOfBirth);
        if (!checkUserExist(username)) {
            dataStorage.getListOfAllUser().put(username, tempUser);
            return true;
        }
        return false;
    }


    public String hashPassword(String password) {
        return MD5.getMd5(password);
    }

    public boolean checkUserExist(String username) {
        if (dataStorage.getListOfAllUser().containsKey(username)) {
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password,String username)
    {
        User user = dataStorage.getListOfAllUser().get(username);
        String hashedPassword = MD5.getMd5(password);
        if(user.getPassword().equals(hashedPassword))
        {
            return true;
        }
        return false;
    }

    public boolean login(String username,String password)
    {
        if(checkUserExist(username))
        {
            if(checkPassword(password,username))
            {
                return true;
            }
        }
        return false;
    }

    public List<String> findFriendByUsername(String keyWord){
        List<String> listFound = new ArrayList<String>();

        return  listFound;
    }
}
