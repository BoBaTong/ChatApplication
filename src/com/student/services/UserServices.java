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
                result="Wrong password! Please try again.";
            }
        }
        else
        {
            result="Wrong username! Please try again.";
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
    public String setAlias(User userSetAlias, User userGetAlias, String alias)
    {
        String result = "Set alias fail!";
        userSetAlias.getAlias().put(userGetAlias,alias);

        if(userSetAlias.getAlias().get(userGetAlias).equals(alias)){
            result="Set alias success!";
        }

        return result;
        //update
    }
}
