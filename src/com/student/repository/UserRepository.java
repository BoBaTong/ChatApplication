package com.student.repository;

import com.student.DataStorage;
import com.student.file.File;
import com.student.group.Group;
import com.student.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    DataStorage dataStorage = DataStorage.getInstance();

    public <T> void save(T obj){
        if (User.class.equals(obj.getClass())) {
            dataStorage.getUserCollection().add((User)obj);
        }
        else if (Group.class.equals(obj.getClass())){
            dataStorage.getGroupCollection().add((Group) obj);
        }
        else if (File.class.equals(obj.getClass())){
            dataStorage.getFileCollection().add((File) obj);
        }
        else {
            throw new InputMismatchException("Please input correctly type");
        }
    }

    /**
     * @author Tong Bo
     * @param id This is id of object
     *
     * @return object by specified id
     * */
    public <T> T findById(UUID id, Class<T> entityClass){
        if (User.class.equals(entityClass)) {
            User result = dataStorage.getUserCollection().stream().filter(u -> u.getId().equals(id)).findFirst().get();
            return (T) result;
        }
        return null;
    }


    /**
     * This function will remove object by id
    * @param id This is id of object
    * @return boolean type

    * */
    public <T>boolean removeById(UUID id,Class<T> entityClass)
    {
        if(User.class.equals(entityClass))
        {
            dataStorage.getUserCollection().removeIf(e ->e.getId().equals(id));
            return true;
        }
        return false;
    }

    public <T> List<T> findAll(Class<T> entityClass)
    {
        if(User.class.equals(entityClass))
        {
            return (List<T>) DataStorage.getInstance().getUserCollection();
        }
        else if(Group.class.equals(entityClass))
        {
            return (List<T>) DataStorage.getInstance().getGroupCollection();
        }
        else if(File.class.equals(entityClass))
        {
            return (List<T>) DataStorage.getInstance().getFileCollection();
        }
        else
        {

            throw new InputMismatchException("Cannot find any information!");
        }
    }

    public static User queryUserByUsername(String username,List<User> userList)
    {
        try {
            Map<String,User> map = userList.stream().collect(Collectors.toMap(User::getUsername, user -> user));
            User result = map.get(username);
            return result;
        }
        catch (Exception e)
        {
            throw new InputMismatchException("Please input correctly");
        }
    }

}