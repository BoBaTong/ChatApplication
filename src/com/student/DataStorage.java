package com.student;

import com.student.file.File;
import com.student.group.Group;
import com.student.group.PrivateGroup;
import com.student.group.PublicGroup;
import com.student.user.User;

import java.lang.reflect.Field;
import java.util.*;

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

    /**
     * @param obj the object that you need add to db based on object's class
     * @throws InputMismatchException if object has no corresponding class
     * */
    public <T> void save(T obj) {
        if (User.class.equals(obj.getClass())) {
            userCollection.add((User) obj);
        } else if (Group.class.equals(obj.getClass())) {
            groupCollection.add((Group) obj);
        }else if (PublicGroup.class.equals(obj.getClass())) {
            groupCollection.add((PublicGroup) obj);
        }
        else if (PrivateGroup.class.equals(obj.getClass())) {
            groupCollection.add((PrivateGroup) obj);
        }
        else if (File.class.equals(obj.getClass())) {
            fileCollection.add((File) obj);
        } else {
            throw new InputMismatchException("Please input correctly type");
        }
    }


    /**
     * @param obj the object that you want to find it's index in db
     * @return the number which is the index of input object
     */
    public <T> int getIndexOfObject(T obj)
    {
        int index;
        if (User.class.equals(obj.getClass())) {
            return index = userCollection.indexOf(obj);
        } else if (Group.class.equals(obj.getClass())) {
            return index = groupCollection.indexOf(obj);
        }else if (PublicGroup.class.equals(obj.getClass())) {
            return index = groupCollection.indexOf(obj);
        }else if (PrivateGroup.class.equals(obj.getClass())) {
            return index = groupCollection.indexOf(obj);
        } else if (File.class.equals(obj.getClass())) {
            return index = fileCollection.indexOf(obj);
        } else {
            throw new InputMismatchException("Please input correctly type");
        }
    }


    /**
     * @param obj the object that you need to update to DB
     * @param index the index of object that you need to update DB
     * update the object with new value based on object's index
     * @throws InputMismatchException if object has no corresponding class
     */
    public <T> void update(T obj,int index)
    {
        if (User.class.equals(obj.getClass())) {
            userCollection.set(index,(User) obj);
        } else if (Group.class.equals(obj.getClass())) {
            groupCollection.set(index,(Group) obj);
        }else if (PublicGroup.class.equals(obj.getClass())) {
            groupCollection.set(index,(Group) obj);
        }else if (PrivateGroup.class.equals(obj.getClass())) {
            groupCollection.set(index,(Group) obj);
        } else if (File.class.equals(obj.getClass())) {
            fileCollection.set(index,(File) obj);
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

    /**
     * @param queryString the value that you want to find
     * @param fieldName a field name of a class
     * @param classes class of object
     * this method will using java reflection to map field->key and object->value
     * @return an object that satisfied the conditions
     * @throws InputMismatchException if object has no corresponding class
     */
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
//            System.out.println(map.keySet());
            if (map.containsKey(queryString)) {
                return (T)map.get(queryString);
            } else {
                return null;
            }
        }
        else if(Group.class.equals(classes)) {
            Field field = Group.class.getDeclaredField(fieldName);
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
//                        System.out.println(map.keySet());
//            System.out.println(map.entrySet());

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
