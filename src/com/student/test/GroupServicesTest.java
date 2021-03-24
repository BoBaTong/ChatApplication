package com.student.test;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.group.GroupType;
import com.student.services.GroupServices;
import com.student.services.UserServices;
import com.student.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

public class GroupServicesTest {
    static GroupServices groupServices;
    static DataStorage dataStorage;
    static Group checkPublicGroup, checkPrivateGroup;
    static UserServices userServices;
    static String inviteCode, createPrivateGroup, createGroupError, joinByInviteCode, joinByInviteCodeError;
    static User userTong, userTu, userBinh;
    static int numOfMemInGroup0, numOfMemInGroup1;
    static String aMemLeaveGroup, notAMemLeaveGroup;

    @BeforeAll
    public static void setUp() throws Exception{
        dataStorage = DataStorage.getInstance();
        groupServices = new GroupServices();
        userServices = new UserServices();

        //set up users
        userServices.saveUser("tongbo","tong","fsf","tongbo","fkwqopfk");
        userServices.saveUser("van","tu","vvtt","tuvan","aabbccdd");
        userServices.saveUser("hai","binh","hb","hbinh","aabbccee");

        //test create group
        createPrivateGroup = groupServices.createGroup(0, 0);

        groupServices.createGroup(1, 1);

        try{
            groupServices.createGroup(2, 1);
        }catch(InputMismatchException e){
            createGroupError = e.getMessage();
        }

        checkPrivateGroup = dataStorage.getGroupCollection().get(0);
        checkPublicGroup = dataStorage.getGroupCollection().get(1);

        //test join group
        userTong = dataStorage.getUserCollection().get(0);
        userTu = dataStorage.getUserCollection().get(1);
        userBinh = dataStorage.getUserCollection().get(2);

        inviteCode = dataStorage.getGroupCollection().get(1).getInviteCode();

        joinByInviteCodeError = groupServices.addMemberByInviteCode("zzz", userBinh);
        joinByInviteCode = groupServices.addMemberByInviteCode(inviteCode, userBinh);

        groupServices.addMember(userTu, userTong, 1);
        groupServices.addMember(userTong, userTu, 0);

        numOfMemInGroup0 = dataStorage.getGroupCollection().get(0).getListOfUser().size();
        numOfMemInGroup1 = dataStorage.getGroupCollection().get(1).getListOfUser().size();

        //test leave group
        notAMemLeaveGroup = groupServices.leaveGroup(userBinh, dataStorage.getGroupCollection().get(0));
        aMemLeaveGroup = groupServices.leaveGroup(userBinh, dataStorage.getGroupCollection().get(1));
    }

    @Test
    public void testCreateGroup() throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("Private group created successful!", createPrivateGroup);

        Assertions.assertEquals("Please input correctly type", createGroupError);

        Assertions.assertEquals(2, dataStorage.getGroupCollection().size());

        Assertions.assertEquals(GroupType.Private, checkPrivateGroup.getGroupType());

        Assertions.assertEquals(GroupType.Public, checkPublicGroup.getGroupType());
    }

    @Test
    public void testJoinGroup() throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("Invalid invite code or group doesn't exist!", joinByInviteCodeError);

        Assertions.assertEquals("You're added to the group.", joinByInviteCode);

        Assertions.assertEquals(2, numOfMemInGroup0);

        Assertions.assertEquals(3, numOfMemInGroup1);
    }

    @Test
    public void testLeaveGroup() throws NoSuchFieldException,IllegalAccessException
    {
        Assertions.assertEquals("You're not a member", notAMemLeaveGroup);

        Assertions.assertEquals("You've left the group", aMemLeaveGroup);
    }
}
