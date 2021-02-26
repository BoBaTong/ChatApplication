package com.student;


import com.student.services.GroupServices;
import com.student.services.UserServices;
import com.student.user.User;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException,IllegalAccessException {

        GroupServices groupServices = new GroupServices();
        UserServices userServices = new UserServices();
        DataStorage dataStorage = DataStorage.getInstance();
        User user = new User("tongbo","tong","fsf","tongbo","fkwqopfk","male","312313");
        groupServices.createGroup(1,user);
        groupServices.createGroup(0,user);

//        groupServices.createGroup(1,user);
        User user2 = new User("tongbo2","tong","fsf","tongbo","fkwqopfk","male","312313");
        groupServices.addMemberPublicGroupByInviteCode("41c36ae4-b035-45d1-89dc-e6f235d45954",user2);
        System.out.println(dataStorage.getPublicGroupCollection().get(0).getId());
//        System.out.println(dataStorage.getPublicGroupCollection().get(1).getId());
        groupServices.addMemberPublicGroupByMember(user,user2,0);
        groupServices.addMemberPrivateGroupByAdmin(user,user2,0);

        System.out.println(dataStorage.getPrivateGroupCollection().get(0).getListOfUser());
        System.out.println(dataStorage.getPrivateGroupCollection().get(0).getAdminGroup());



    }
}
