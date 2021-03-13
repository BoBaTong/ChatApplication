package com.student.group;

import com.student.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PublicGroup extends Group {
    private static int count = 0;
    private int id=0;
    private List<User>listOfUser = new ArrayList<User>();
    private List<User> adminGroup = new ArrayList<>();
    private static String inviteCode;


    public List<User> getListOfUser() {
        return listOfUser;
    }

    public int getId() {
        return id;
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

    public PublicGroup() {
        groupMessages = new ArrayList<>();
        groupFiles = new ArrayList<>();
//        inviteCode = UUID.randomUUID().toString();
        inviteCode = "41c36ae4-b035-45d1-89dc-e6f235d45954";
        id=count++;
    }

    public String getInviteCode() {
        return inviteCode;
    }



}
