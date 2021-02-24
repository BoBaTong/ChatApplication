package com.student.repository;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.user.User;

import java.util.InputMismatchException;

public class GroupRepository extends Repository {
    DataStorage dataStorage = DataStorage.getInstance();

    @Override
    public <T> void save(T obj) {
        if (User.class.equals(obj.getClass())) {
            dataStorage.getGroupCollection().add((Group) obj);
        }
        else {
            throw new InputMismatchException("Please input correctly type");
        }
    }

    public <T> void saveMember (T obj)
    {
        //
    }
}
