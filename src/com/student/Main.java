package com.student;


import com.student.message.ContentType;
import com.student.message.Message;
import com.student.services.GroupServices;
import com.student.services.MessageServices;
import com.student.services.UserServices;
import com.student.user.User;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException,IllegalAccessException {

        GroupServices groupServices = new GroupServices();
        UserServices userServices = new UserServices();
        DataStorage dataStorage = DataStorage.getInstance();
        MessageServices messageServices = new MessageServices();
//        User user = new User("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313");
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313");
        groupServices.createGroup(1,0);

//        groupServices.createGroup(1,user);
        User user2 = new User("tongbo2","tong","fsf","tongbo","fkwqopfk","male","312313");
//        groupServices.addMemberPublicGroupByInviteCode("41c36ae4-b035-45d1-89dc-e6f235d45954",user2);

        System.out.println("id"+dataStorage.getPublicGroupCollection().get(0).getId());
//        System.out.println(dataStorage.getPublicGroupCollection().get(1).getId());
//        groupServices.addMemberPublicGroupByMember(user,user2,1);
//        System.out.println(dataStorage.getPublicGroupCollection().get(1).getListOfUser().size());
        System.out.println(dataStorage.getPublicGroupCollection().get(0).getListOfUser().size());
        System.out.println("show"+dataStorage.getUserCollection().size());
//        groupServices.addMemberPrivateGroupByAdmin(user,user2,0);

//        System.out.println(dataStorage.getPrivateGroupCollection().get(0).getListOfUser());
//        System.out.println(dataStorage.getPrivateGroupCollection().get(0).getAdminGroup());
        Message message = new Message("ccccccccc", ContentType.Text);
        messageServices.sendMessage(message,0,0);
        messageServices.showMessagePublicGroup(0,dataStorage.getPublicGroupCollection().get(0));


    }
}
