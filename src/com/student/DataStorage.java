package com.student;

import com.student.file.File;
import com.student.group.Group;
import com.student.group.PrivateGroup;
import com.student.user.User;

import javax.imageio.spi.ImageReaderSpi;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    public <T> void save(T obj) {
        if (User.class.equals(obj.getClass())) {
            userCollection.add((User) obj);
        } else if (Group.class.equals(obj.getClass())) {
            groupCollection.add((Group) obj);
        } else if (File.class.equals(obj.getClass())) {
            fileCollection.add((File) obj);
        } else {
            throw new InputMismatchException("Please input correctly type");
        }
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        if (User.class.equals(entityClass)) {
            return (List<T>) this.getUserCollection();
        } else if (Group.class.equals(entityClass)) {
            return (List<T>) this.getGroupCollection();
        } else if (File.class.equals(entityClass)) {
            return (List<T>) this.getFileCollection();
        } else {

            throw new InputMismatchException("Cannot find any information!");
        }
    }

    public <T> T findByField(String queryString, String fieldName, Class<T> classes) throws NoSuchFieldException,IllegalAccessException {
        if(User.class.equals(classes)) {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Map<String,User>map = new HashMap<>();
            for (User user : getUserCollection())
            {
                map.put(field.get(user).toString(),user);
            }
//            Map<String, User> map = this
//                .getUserCollection()
//                    .stream()
//                    .collect(Collectors.toMap(User::getUsername, user -> user));
            System.out.println(map.keySet());
            if (map.containsKey(queryString)) {
                return (T)map.get(queryString);
            } else {
                return null;
            }
        }
        else if(Group.class.equals(classes)) {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Map<String,Group>map = new HashMap<>();
            for (Group group : getGroupCollection())
            {
                map.put(field.get(group).toString(),group);
            }
//            Map<String, User> map = this
//                .getUserCollection()
//                    .stream()
//                    .collect(Collectors.toMap(User::getUsername, user -> user));
            if (map.containsKey(queryString)) {
                return (T)map.get(queryString);
            } else {
                return null;
            }
        }
        else {

            throw new InputMismatchException("Cannot find any information!");
        }
    }
}
