package com.student;

import com.student.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStorage {
    private static DataStorage instance;
    private static HashMap<String,User> listOfAllUser = null;

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    private DataStorage()
    {
        listOfAllUser = new HashMap<String, User>();
    }
    public HashMap<String,User> getListOfAllUser()
    {
        return this.listOfAllUser;
    }
    public void addToList(String username,User user) {
        listOfAllUser.put(username,user);
    }
}
