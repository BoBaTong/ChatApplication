package com.student;

import com.student.message.ContentType;
import com.student.message.Message;
import com.student.services.GroupServices;
import com.student.services.MessageServices;
import com.student.services.UserServices;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        GroupServices groupServices = new GroupServices();
        UserServices userServices = new UserServices();
        DataStorage dataStorage = DataStorage.getInstance();
        MessageServices messageServices = new MessageServices();
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");

//        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313");
//        userServices.saveUser("tongbo2","tong2","fsf","tongbo2","fkwqopfk","male","312313");

        groupServices.createGroup(1,0);
        groupServices.createGroup(0,0);
        Message message = new Message("ccccccccc", ContentType.Text);
        Message message2 = new Message("ccccccccc2222", ContentType.Text);
        Message message3 = new Message("user1 sent to user2", ContentType.Text);

//        System.out.println("show2"+dataStorage.getGroupCollection().size());
//        messageServices.sendMessageUserToGroup(message,0,0);
//        messageServices.sendMessageUserToGroup(message2,0,1);
//        messageServices.showMessageGroup(0,0);
//        messageServices.showMessageGroup(0,1);
//        messageServices.sendMessageUserToUser(message3,0,1);
//        messageServices.showMessageUser(0,1);
//        System.out.println(dataStorage.getGroupCollection().get(0).getInviteCode());
//        System.out.println(dataStorage.getGroupCollection().get(1).getInviteCode());
//        System.out.println(dataStorage.getGroupCollection().get(0).getListOfUser().size());
//
//        groupServices.addMemberByInviteCode(dataStorage.getGroupCollection().get(1).getInviteCode(),dataStorage.getUserCollection().get(1));
//        System.out.println(dataStorage.getGroupCollection().get(0).getListOfUser().size());
        System.out.println("3 "+dataStorage.getGroupCollection().size());
        System.out.println("2 "+dataStorage.getUserCollection().size());
        System.out.println("1 "+dataStorage.getGroupCollection().get(0).getListOfUser());

        groupServices.leaveGroup(dataStorage.getUserCollection().get(0),dataStorage.getGroupCollection().get(0));
        System.out.println(dataStorage.getGroupCollection().get(0).getListOfUser());
    }
}
