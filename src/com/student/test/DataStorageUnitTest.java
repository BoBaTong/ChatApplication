package com.student.test;

import com.student.DataStorage;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DataStorageUnitTest {

    @Test
    public void savingUserTest() {
        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
        DataStorage.getInstance().save(user);
        Assertions.assertEquals(1, DataStorage.getInstance().getListUser().size());
    }

    @Test
    public void getUserTest() {
        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
        DataStorage.getInstance().save(user);
        User result = DataStorage.getInstance().findById("tongbo", User.class);
        Assertions.assertEquals("tongbo", result.getUsername());
    }
}
