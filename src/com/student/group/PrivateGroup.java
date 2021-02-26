package com.student.group;

import com.student.user.User;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group {
    private static int count = 0;
    private int id=0;


    public PrivateGroup() {
        id=count++;
    }

    private List<User> listOfUser = new ArrayList<User>();

    private List<User> adminGroup = new ArrayList<>();

    public List<User> getListOfUser() {
        return listOfUser;
    }

    public void setListOfUser(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }

    public List<User> getAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(List<User> adminGroup) {
        this.adminGroup = adminGroup;
    }
}
