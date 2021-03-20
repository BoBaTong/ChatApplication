package com.student.repository;

import com.student.DataStorage;
import com.student.group.Group;

import java.util.List;

public class GroupRepository extends Repository {
    DataStorage dataStorage = DataStorage.getInstance();

    @Override
    public <T> void save(T obj) {
        dataStorage.save(obj);
    }

    public <T> List<T> findAll(Class<T> entityClass)
    {
        return dataStorage.findAll(entityClass);
    }
    public <T> void update(T obj,int objIndex)
    {
        dataStorage.update(obj,objIndex);
    }

    public <T> int getIndexOfObject(T obj)
    {
        return dataStorage.getIndexOfObject(obj);
    }

    public Group getGroupByInviteCode  (String queryString) throws NoSuchFieldException,IllegalAccessException{
        Group group = dataStorage.findByField(queryString,"inviteCode", Group.class);
        return group;
    }
    public Group getGroupByID(int groupID) throws NoSuchFieldException,IllegalAccessException{
        Group group = dataStorage.findByField(groupID+"","id", Group.class);
        return group;
    }





}
