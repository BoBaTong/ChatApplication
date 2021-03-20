package com.student.test;

import com.student.DataStorage;
import com.student.services.UserServices;
import org.junit.jupiter.api.Test;

public class UserServicesTest {
    DataStorage dataStorage = DataStorage.getInstance();

    @Test
    public void UserServicesTest()throws NoSuchFieldException,IllegalAccessException
    {
        UserServices userServices = new UserServices();
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");
        userServices.login("tongbo","fkwqopfk");
    }
}
