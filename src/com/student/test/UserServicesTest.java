package com.student.test;

import com.student.DataStorage;
import com.student.repository.UserRepository;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Test;

public class UserServicesTest {
    @Test
    public void UserServicesTest()
    {
        UserServices userServices = new UserServices();
        UserRepository userRepository = new UserRepository();
        userRepository.save(new User("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313"));
        userRepository.save(new User("tongbo","tong","fsf","btong","fkwqopfk","male","312313"));
        System.out.println(userServices.login("btong","fsfsdf"));
    }
}
