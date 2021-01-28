package com.student;

import com.student.file.File;
import com.student.group.Group;
import com.student.group.PrivateGroup;
import com.student.user.User;

import javax.imageio.spi.ImageReaderSpi;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

public class DataStorage {
    private static DataStorage instance;
    private static List<User> userCollection;
    private static List<Group> groupCollection;
    private static List<File> fileCollection;


    public static synchronized DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
            userCollection = new ArrayList<>();
            groupCollection = new ArrayList<>();
            fileCollection = new ArrayList<>();
        }
        return instance;
    }
    //dependency injection

    public List<User> getUserCollection() {
        return userCollection;
    }

    public List<Group> getGroupCollection() {
        return groupCollection;
    }

    public List<File> getFileCollection() {
        return fileCollection;
    }

    public <T> void save(T obj){
        if (User.class.equals(obj.getClass())) {
            userCollection.add((User)obj);
        }
        else if (Group.class.equals(obj.getClass())){
            groupCollection.add((Group) obj);
        }
        else if (File.class.equals(obj.getClass())){
            fileCollection.add((File) obj);
        }
        else {
            throw new InputMismatchException("Please input correctly type");
        }
    }



}
