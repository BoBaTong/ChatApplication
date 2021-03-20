package com.student.test;

import com.student.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;

class DataStorageUnitTest {
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception{
        userRepository = new UserRepository();
    }

//    @Test
//    public void findByFieldTest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
//        UserRepository userRepository = new UserRepository();
//        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
//        User user2 = new User("Tong2", "Bo", "Bo Ba Tong2", "tongbo2", "123456", "Male", "11/04/1997");
//
//        userRepository.save(user);
//        userRepository.save(user2);
//
//        User user3 = DataStorage.getInstance().findByField("Bo","firstName",User.class);
//        //Assertions.assertEquals();
//        System.out.println(user3);
//    }
//    @Test
//    void getUserTest() {
//        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
//
//        userRepository.save(user);
////        User result = userServices.findById(user.getId(), User.class);
//        userRepository.removeById(user.getId(),User.class);
//        Assertions.assertEquals(0, DataStorage.getInstance().getUserCollection().size());
//    }
//
//
//    @Test
//    public void findAllTest()
//    {
//        User user = new User("Tong", "Bo", "Bo Ba Tong", "tongbo", "123456", "Male", "11/04/1997");
//        User user2 = new User("Tong2", "Bo2", "Bo Ba Tong2", "tongbo2", "123456", "Male", "11/04/1997");
//        userRepository.save(user);
//        userRepository.save(user2);
//        Assertions.assertEquals(2, DataStorage.getInstance().getUserCollection().size());
//    }
}
