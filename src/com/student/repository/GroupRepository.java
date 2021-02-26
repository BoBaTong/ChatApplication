package com.student.repository;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.group.PrivateGroup;
import com.student.group.PublicGroup;
import com.student.user.User;

import java.util.InputMismatchException;

public class GroupRepository extends Repository {
    DataStorage dataStorage = DataStorage.getInstance();

    @Override
    public <T> void save(T obj) {
        dataStorage.save(obj);
    }

    public PublicGroup getGroupByInviteCode  (String queryString) throws NoSuchFieldException,IllegalAccessException{
        PublicGroup publicGroup = dataStorage.findByField(queryString,"inviteCode",PublicGroup.class);
        return publicGroup;
    }
    public PublicGroup getGroupByID  (int groupID) throws NoSuchFieldException,IllegalAccessException{
        PublicGroup publicGroup = dataStorage.findByField(groupID+"","id",PublicGroup.class);
        return publicGroup;
    }
    public PrivateGroup getPrivateGroupByID  (int groupID) throws NoSuchFieldException,IllegalAccessException{
        PrivateGroup privateGroup = dataStorage.findByField(groupID+"","id", PrivateGroup.class);
        return privateGroup;
    }





}
