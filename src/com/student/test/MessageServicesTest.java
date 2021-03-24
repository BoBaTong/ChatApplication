package com.student.test;

import com.student.DataStorage;
import com.student.file.File;
import com.student.message.Message;
import com.student.repository.UserRepository;
import com.student.services.GroupServices;
import com.student.services.MessageServices;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MessageServicesTest {
    static GroupServices groupServices;
    static UserServices userServices;
    static MessageServices messageServices;
    static User userTong, userTu;
    static DataStorage dataStorage;
    static Message messageOfTu, messageOfTong;
    static File fileOfTu, fileOfTong;

    @BeforeAll
    public static void setUp() throws Exception{
        dataStorage = DataStorage.getInstance();
        groupServices = new GroupServices();
        userServices = new UserServices();
        messageServices = new MessageServices();

        //set up users
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");
        userServices.saveUser("van","tu","vvtt","tuvan","aabbccdd");
        userTong = dataStorage.getUserCollection().get(0);
        userTu = dataStorage.getUserCollection().get(1);

        //set up groups
        groupServices.createGroup(0, 0);
        groupServices.createGroup(1, 1);

        //set up messages
        messageOfTong = new Message("What's up man");
        messageOfTu = new Message("Hi I'm a new member");

        //add user Tong to group 1
        groupServices.addMember(userTu, userTong, 1);

        //Tong send message to Tu
        messageServices.sendMessageToUser(messageOfTong, 0, 1);

        //Tu send message to group 1
        messageServices.sendMessageToGroup(messageOfTu, 1, 1);

        //set up files
        fileOfTong = new File("cat.png", "Desktop/cat.png");
        fileOfTu = new File("dog.png", "Desktop/dog.png");

        //Tong send file to Tu
        messageServices.sendFileToUser(fileOfTong, 1, 0);

        //Tu send file to group 1
        messageServices.sendFileToGroup(fileOfTu, 1, 1);
    }

    @Test
    public void testSendMessage()throws NoSuchFieldException,IllegalAccessException
    {
        String receivedMessage = dataStorage.getUserCollection().get(1).getReceivedMessages()
                .get(0).getContent();
        Assertions.assertEquals("What's up man", receivedMessage);

        receivedMessage = dataStorage.getGroupCollection().get(1).getGroupMessages().get(0)
                .getContent();
        Assertions.assertEquals("Hi I'm a new member", receivedMessage);
    }

    @Test
    public void testFileMessage()throws NoSuchFieldException,IllegalAccessException
    {

    }
}
