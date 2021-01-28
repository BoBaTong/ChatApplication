package com.student.test;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.services.UserRepository;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataStorageUnitTest {


    @Test
    public void getUserTest() {
        UserRepository userRepository = new UserRepository();
        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
        userRepository.save(user);
//        User result = userServices.findById(user.getId(), User.class);
        userRepository.removeById(user.getId(),User.class);
        Assertions.assertEquals(0, DataStorage.getInstance().getUserCollection().size());
    }
    @Test
    public void findAllTest()
    {
        UserRepository userRepository = new UserRepository();
        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
        User user2 = new User("Tong2", "Bo2", "Bo Ba Tong2", "tongbo2", "123456", "Male", "11/04/1997");
        userRepository.save(user);
        userRepository.save(user2);
        List list = userRepository.findAll(Group.class);
        Assertions.assertEquals(2, DataStorage.getInstance().getUserCollection().size());
    }
}
