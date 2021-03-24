package com.student.test;

import com.student.DataStorage;
import com.student.repository.UserRepository;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServicesTest {
    static DataStorage dataStorage;
    static UserServices userServices;
    static UserRepository userRepository;
    static User userTong, userTu;
    static String loginSuccess, loginWrongPassword, loginWrongUsername, resOfSetAlias;

    @BeforeAll
    public static void setUp() throws Exception{
        dataStorage = DataStorage.getInstance();
        userServices = new UserServices();
        userRepository = new UserRepository();

        //create new users
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");
        userServices.saveUser("van","tu","vvtt","tuvan","aabbccdd");

        //test login
        loginSuccess = userServices.login("tongbo","fkwqopfk");
        loginWrongPassword = userServices.login("tongbo","fkwqop");
        loginWrongUsername = userServices.login("tongb","fkwqop");

        //test find friend
        userTong = userRepository.getUserByUsername("tongbo");
        userTu = userRepository.getUserByID(1);

        //test set alias
        resOfSetAlias = userServices.setAlias(userTong, userTu, "Tin");
    }

    @Test
    public void testSaveUser()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertTrue(userServices.checkUserExist("tongbo"));
        Assertions.assertTrue(userServices.checkUserExist("tuvan"));
    }

    @Test
    public void testLogin()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("Login success!", loginSuccess);

        Assertions.assertEquals("Wrong password! Please try again.", loginWrongPassword);

        Assertions.assertEquals("Wrong username! Please try again.", loginWrongUsername);
    }

    @Test
    public void testFindFriend()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("tong", userTong.getFirstName());

        Assertions.assertEquals("vvtt", userTu.getFullName());
    }

    @Test
    public void testSetAlias()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("Set alias success!", resOfSetAlias);
    }
}
