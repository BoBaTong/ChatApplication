package com.student.services;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.group.GroupType;
import com.student.group.PrivateGroup;
import com.student.group.PublicGroup;
import com.student.repository.GroupRepository;
import com.student.repository.UserRepository;
import com.student.user.User;

import java.util.InputMismatchException;

public class GroupServices {

    GroupRepository groupRepository = new GroupRepository();
    DataStorage dataStorage = DataStorage.getInstance();
    UserRepository userRepository = new UserRepository();


    public String createGroup(int groupSelection, int userID) throws NoSuchFieldException, IllegalAccessException {
        String result;
        User user = userRepository.getUserByID(userID);
        if (groupSelection == 0) {
            PrivateGroup group = new PrivateGroup();
            group.getAdminGroup().add(user);
            group.getListOfUser().add(user);
            groupRepository.save(group);
            result="Private group created successful!";
        } else if (groupSelection == 1) {
            PublicGroup group = new PublicGroup();
            group.getAdminGroup().add(user);
            group.getListOfUser().add(user);
            groupRepository.save(group);
            result="Public group created successful!, invite code: "+ group.getInviteCode();
        } else
            throw new InputMismatchException("Please input correctly type");
        return result;
    }

    public void addMember(User currentUser, User invitedUser, int groupID) throws NoSuchFieldException, IllegalAccessException {
        Group group = groupRepository.getGroupByID(groupID);
        if (group.getGroupType().equals(GroupType.Private)) {
            if (checkAdminPrivateGroup(currentUser, groupID)) {
                group.getListOfUser().add(invitedUser);
                groupRepository.update(group, groupRepository.getIndexOfObject(group));
            }
        } else {
            group.getListOfUser().add(invitedUser);
            groupRepository.update(group, groupRepository.getIndexOfObject(group));

        }
    }

    //giả định trường hợp method này chỉ xuất hiện trong public group
    public String addMemberByInviteCode(String inviteCode, User currentUser) throws NoSuchFieldException, IllegalAccessException {
        String result;
        Group group = groupRepository.getGroupByInviteCode(inviteCode);
        if (group != null) {
            group.getListOfUser().add(currentUser);
            groupRepository.update(group, groupRepository.getIndexOfObject(group));
            result="You're added to the group.";
        } else
            result="Invalid invite code or group doesnt exist!.";
        return result;
    }

    //    public void addMemberPublicGroupByMember(User currentUser, User invitedUser,int groupID)throws NoSuchFieldException,IllegalAccessException
//    {
//        PublicGroup publicGroup = groupRepository.getPublicGroupByID(groupID);
//        if(publicGroup!= null)
//        {
//            if(checkMemberGroup(currentUser,groupID,PublicGroup.class))
//            {
//                if(checkMemberGroup(invitedUser,groupID,PublicGroup.class))
//                {
//                    System.out.println("Your friend is already a member");
//                }
//                else {
//                    publicGroup.getListOfUser().add(invitedUser);
//                    groupRepository.update(publicGroup,groupRepository.getIndexOfObject(publicGroup));
//                }
//            }
//            else
//            {
//                System.out.println("You're not a member");
//            }
//        }
//        else
//            System.out.println("Group does not exist!");
//
//
//    }
    private boolean checkAdminPrivateGroup(User admin, int groupID) throws NoSuchFieldException, IllegalAccessException {
        Group group = groupRepository.getGroupByID(groupID);
        if (group != null) {
            if (group.getAdminGroup().contains(admin)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public <T> boolean checkMemberGroup(User user, int groupID) throws NoSuchFieldException, IllegalAccessException {
            Group group = groupRepository.getGroupByID(groupID);
            if (group != null) {
                if (group.getListOfUser().contains(user)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
    }

    private boolean checkGroupExistByInviteCode(String inviteCode) throws NoSuchFieldException, IllegalAccessException {
        if (groupRepository.getGroupByInviteCode(inviteCode) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String leaveGroup(User user,Group group) throws NoSuchFieldException, IllegalAccessException {
        String result;
        if(checkMemberGroup(user,group.getId()))
        {
            group.getListOfUser().remove(user);
            groupRepository.update(group,groupRepository.getIndexOfObject(group));
            result ="You've left the group";
        }
        else
        {
            result="You're not a member";
        }
        return result;
    }


}
