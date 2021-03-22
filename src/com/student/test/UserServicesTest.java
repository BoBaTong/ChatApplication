package com.student.test;

import com.student.DataStorage;
import com.student.repository.UserRepository;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServicesTest {
    DataStorage dataStorage;
    UserServices userServices;
    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() throws Exception{
        dataStorage = DataStorage.getInstance();
        userServices = new UserServices();
        userRepository = new UserRepository();
    }

    @Test
    public void testSaveUser()throws NoSuchFieldException,IllegalAccessException
    {
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");
        Assertions.assertTrue(userServices.checkUserExist("tongbo"));

        userServices.saveUser("van","tu","vvtt","tuvan","aabbccdd");
        Assertions.assertTrue(userServices.checkUserExist("tuvan"));
    }

    @Test
    public void testLogin()throws NoSuchFieldException,IllegalAccessException
    {
        String res = userServices.login("tongbo","fkwqopfk");
        Assertions.assertEquals("Login success!", res);

        res = userServices.login("tongbo","fkwqop");
        Assertions.assertEquals("Wrong password! Please try again.", res);

        res = userServices.login("tongb","fkwqop");
        Assertions.assertEquals("Wrong username! Please try again.", res);
    }

    @Test
    public void testFindFriend()throws NoSuchFieldException,IllegalAccessException
    {
        user = userRepository.getUserByUsername("tongbo");
        Assertions.assertEquals("tong", user.getFirstName());

        user = userRepository.getUserByID(2);
        Assertions.assertEquals("vvtt", user.getFullName());
    }
}
