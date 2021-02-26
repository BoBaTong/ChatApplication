package com.student.services;

import com.student.repository.UserRepository;
import com.student.user.User;

public class UserServices {
    UserRepository userRepository = new UserRepository();
    MD5 md5 = new MD5();

    public boolean saveUser(String lastName, String firstName, String fullName, String username, String password, String gender, String dateOfBirth)throws NoSuchFieldException,IllegalAccessException
    {
        String hashedPassword = MD5.getMd5(password);
        if(!checkUserExist(username))
        {
            User user = new User(lastName,firstName,fullName,username,hashedPassword,gender,dateOfBirth);
            userRepository.save(user);
            return true;
        }
        else
            return false;

    }

    public void login(String username, String password)throws NoSuchFieldException,IllegalAccessException
    {
        if(checkUserExist(username))
        {
            if(checkHashPassword(username,password))
            {
                System.out.println("Login success!");
            }
            else
            {
                System.out.println("Looks like these are not your correct details. Please try again.");
            }
        }
        else
        {
            System.out.println("Looks like these are not your correct details. Please try again.");
        }

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
}
