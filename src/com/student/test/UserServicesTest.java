package com.student.test;

import com.student.DataStorage;
import com.student.repository.UserRepository;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServicesTest {
    DataStorage dataStorage = DataStorage.getInstance();

    @Test
    public void UserServicesTest()throws NoSuchFieldException,IllegalAccessException
    {
        UserServices userServices = new UserServices();
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313");
        userServices.login("tongbo","fkwqopfk");
    }
}
