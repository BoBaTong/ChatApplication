package com.student.services;

import com.student.repository.UserRepository;
import com.student.user.User;

public class UserServices {
    UserRepository userRepository = new UserRepository();

    public boolean saveUser(String lastName, String firstName, String fullName, String username, String password)throws NoSuchFieldException,IllegalAccessException
    {
        String hashedPassword = MD5.getMd5(password);
        if(!checkUserExist(username))
        {
            User user = new User(lastName,firstName,fullName,username,hashedPassword);
            userRepository.save(user);
            return true;
        }
        else
            return false;

    }

    public String login(String username, String password)throws NoSuchFieldException,IllegalAccessException
    {
        String result;
        if(checkUserExist(username))
        {
            if(checkHashPassword(username,password))
            {
                result="Login success!";
            }
            else
            {
                result="Looks like these are not your correct details. Please try again.";
            }
        }
        else
        {
            result="Looks like these are not your correct details. Please try again.";
        }
        return result;
    }

    public boolean checkHashPassword(String username,String password) throws NoSuchFieldException,IllegalAccessException
    {
        String hashedPassword = MD5.getMd5(password);
           return userRepository.getUserByUsername(username).getPassword().equals(hashedPassword);

    }
    public boolean checkUserExist(String username)  throws NoSuchFieldException,IllegalAccessException
    {
         if(userRepository.getUserByUsername(username) != null)
        {
            return true;
        }
         else
             return false;
    }
    public String setAlias(User user1,User user2,String alias)
    {
        String result;
        user1.getAlias().put(user2,alias);
        return result="Set alias success!";
        //update
    }
}
