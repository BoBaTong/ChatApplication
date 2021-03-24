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

import java.util.List;

public class MessageServicesTest {
    static GroupServices groupServices;
    static UserServices userServices;
    static MessageServices messageServices;
    static User userTong, userTu;
    static DataStorage dataStorage;
    static Message messageOfTu, messageOfTong, messageTest1, messageTest2;
    static File fileOfTu, fileOfTong, fileTest1, fileTest2;
    static String tuReceivedMess, tuReceivedFile, group1ReceivedMess, group1ReceivedFile;
    static int numOfTuMessBeforeDel, numOfTuMessAfterDel, numOfTuFileBeforeDel, numOfTuFileAfterDel,
            numOfG1MessBeforeDel, numOfG1MessAfterDel, numOfG1FileBeforeDel, numOfG1FileAfterDel;
    static int numOfFileTuSentToG1, numOfKExceptM, numOfMessFoundUser, numOfMessFoundGroup;
    static List<File> filesTuSentToG1;
    static List<Message> listShowKExceptM, listMessFoundUser, listMessFoundGroup;

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
        tuReceivedMess = userTu.getReceivedMessages().get(0).getContent();

        //Tu send message to group 1
        messageServices.sendMessageToGroup(messageOfTu, 1, 1);
        group1ReceivedMess = dataStorage.getGroupCollection().get(1).getGroupMessages()
                .get(0).getContent();

        //set up files
        fileOfTong = new File("cat.png", "Desktop/cat.png");
        fileOfTu = new File("dog.png", "Desktop/dog.png");

        //Tong send file to Tu
        messageServices.sendFileToUser(fileOfTong, 1, 0);
        tuReceivedFile = userTu.getReceivedFiles().get(0).getFileName();

        //Tu send file to group 1
        messageServices.sendFileToGroup(fileOfTu, 1, 1);
        group1ReceivedFile = dataStorage.getGroupCollection().get(1).getGroupFiles()
                .get(0).getFileName();

        //add more messages and files to delete
        messageTest1 = new Message("Hello");
        messageTest2 = new Message("Haha");
        fileTest1 = new File("cow.png", "Desktop/cow.png");
        fileTest2 = new File("ant.png", "Desktop/ant.png");

        //Tong send messageTest1 to Tu
        messageServices.sendMessageToUser(messageTest1, 0, 1);
        //Tong send fileTest1 to Tu
        messageServices.sendFileToUser(fileTest1, 1, 0);
        //Tu send messageTest2 to group 1
        messageServices.sendMessageToGroup(messageTest2, 1, 1);
        //Tu send fileTest2 to group 1
        messageServices.sendFileToGroup(fileTest2, 1, 1);

        numOfTuMessBeforeDel = userTu.getReceivedMessages().size();
        numOfTuFileBeforeDel = userTu.getReceivedFiles().size();
        numOfG1MessBeforeDel = dataStorage.getGroupCollection().get(1).getGroupMessages().size();
        numOfG1FileBeforeDel = dataStorage.getGroupCollection().get(1).getGroupFiles().size();

        //delete message user
        messageServices.deleteMessageUser(userTong, userTu, messageTest1);
        //delete file user
        messageServices.deleteFileUser(userTong, userTu, fileTest1);
        //delete message in group
        messageServices.deleteMessageInGroup(dataStorage.getGroupCollection().get(1),
                userTu, messageTest2);
        //delete file in group
        messageServices.deleteFileInGroup(dataStorage.getGroupCollection().get(1),
                userTu, fileTest2);

        numOfTuMessAfterDel = userTu.getReceivedMessages().size();
        numOfTuFileAfterDel = userTu.getReceivedFiles().size();
        numOfG1MessAfterDel = dataStorage.getGroupCollection().get(1).getGroupMessages().size();
        numOfG1FileAfterDel = dataStorage.getGroupCollection().get(1).getGroupFiles().size();

        //test files tu sent to group 1
        filesTuSentToG1 = messageServices.showAllFileInGroup(userTu, dataStorage.getGroupCollection().get(1));
        numOfFileTuSentToG1 = filesTuSentToG1.size();

        //test show K latest mess except M latest mess in group
        //Tu send again messageTest2 to group 1 for test
        messageServices.sendMessageToGroup(messageTest2, 1, 1);
        //show 2 latest mess except 1 latest mess in group 1
        listShowKExceptM = messageServices.showLatestMessageGroupExceptM(1, 1, 2, 1);
        numOfKExceptM = listShowKExceptM.size();

        //test find mess by keyword
        listMessFoundUser = messageServices.findMessageByKeywordInUser("up", userTong, userTu);
        numOfMessFoundUser = listMessFoundUser.size();

        listMessFoundGroup = messageServices.findMessageByKeywordInGroup("new", dataStorage
                .getGroupCollection().get(1));
        numOfMessFoundGroup = listMessFoundGroup.size();
    }

    @Test
    public void testSendFileMessage()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("What's up man", tuReceivedMess);

        Assertions.assertEquals("Hi I'm a new member", group1ReceivedMess);

        Assertions.assertEquals("cat.png", tuReceivedFile);

        Assertions.assertEquals("dog.png", group1ReceivedFile);
    }

    @Test
    public void testDeleteFileMessage()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals(2, numOfTuMessBeforeDel);
        Assertions.assertEquals(2, numOfTuFileBeforeDel);
        Assertions.assertEquals(2, numOfG1MessBeforeDel);
        Assertions.assertEquals(2, numOfG1FileBeforeDel);

        Assertions.assertEquals(1, numOfTuMessAfterDel);
        Assertions.assertEquals(1, numOfTuFileAfterDel);
        Assertions.assertEquals(1, numOfG1MessAfterDel);
        Assertions.assertEquals(1, numOfG1FileAfterDel);
    }

    @Test
    public void testFilesSentToGroup()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals(1, numOfFileTuSentToG1);
        Assertions.assertEquals("dog.png", filesTuSentToG1.get(0).getFileName());
    }

    @Test
    public void testShowKExceptMMessInGroup()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals(1, numOfKExceptM);
        Assertions.assertEquals("Hi I'm a new member", listShowKExceptM.get(0).getContent());
    }

    @Test
    public void testFindMessByKeyword()throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals(1, numOfMessFoundUser);
        Assertions.assertEquals("What's up man", listMessFoundUser.get(0).getContent());

        Assertions.assertEquals(1, numOfMessFoundGroup);
        Assertions.assertEquals("Hi I'm a new member", listMessFoundGroup.get(0).getContent());
    }
}
