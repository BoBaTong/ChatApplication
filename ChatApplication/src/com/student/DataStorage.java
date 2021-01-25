package com.student;

import com.student.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStorage {
    private static DataStorage instance;
    private static HashMap<String,User> listOfAllUser = new HashMap<>();

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public HashMap<String, User> getListOfAllUser() {
        return listOfAllUser;
    }
}
