package com.student.group;

import com.student.file.File;
import com.student.message.Message;
import com.student.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Group {
    private static int count = 0;
    private int id=0;
    private List<User>listOfUser = new ArrayList<User>();
    private List<User> adminGroup = new ArrayList<>();
    public List<Message> groupMessages = new ArrayList<>();
    public List<File> groupFiles = new ArrayList<>();
    private GroupType groupType;
    private final String inviteCode = UUID.randomUUID().toString();

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Group() {
        id=++count;
    }

    public String getInviteCode() {
        return inviteCode;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Message> getGroupMessages() {
        return groupMessages;
    }

    public void setGroupMessages(List<Message> groupMessages) {
        this.groupMessages = groupMessages;
    }

    public List<File> getGroupFiles() {
        return groupFiles;
    }

    public void setGroupFiles(List<File> groupFiles) {
        this.groupFiles = groupFiles;
    }


}
