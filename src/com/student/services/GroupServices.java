package com.student.services;

import com.student.DataStorage;
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


    public void createGroup(int groupSelection, int userID) throws NoSuchFieldException, IllegalAccessException {
        User user = userRepository.getUserByID(userID);
        if(groupSelection==0)
        {
            PrivateGroup privateGroup = new PrivateGroup();
            privateGroup.getAdminGroup().add(user);
            privateGroup.getListOfUser().add(user);
            groupRepository.save(privateGroup);
            System.out.println("Private group created successful!");
        }
        else if(groupSelection==1)
        {
            PublicGroup publicGroup = new PublicGroup();
            publicGroup.getAdminGroup().add(user);
            publicGroup.getListOfUser().add(user);
            groupRepository.save(publicGroup);
            System.out.println("Public group created successful!, invite code: "+publicGroup.getInviteCode());
        }
        else
            throw new InputMismatchException("Please input correctly type");
    }

    public void addMemberPrivateGroupByAdmin(User admin, User invitedUser,int groupID)throws NoSuchFieldException,IllegalAccessException
    {
        PrivateGroup privateGroup = groupRepository.getPrivateGroupByID(groupID);
        if(privateGroup!= null)
        {
            if(checkAdminPrivateGroup(admin,groupID))
            {
                if(checkMemberGroup(invitedUser,groupID,PrivateGroup.class))
                {
                    System.out.println("Your friend is already a member");
                }
                else {
                    privateGroup.getListOfUser().add(invitedUser);
                    groupRepository.save(privateGroup);
                }
            }
            else
            {
                System.out.println("You're not an admin");
            }
        }
        else
            System.out.println("Group does not exist!");
    }

    public void addMemberPublicGroupByInviteCode(String inviteCode,User currentUser)throws NoSuchFieldException,IllegalAccessException
    {
        PublicGroup publicGroup = groupRepository.getGroupByInviteCode(inviteCode);
        if(publicGroup!=null)
        {
            publicGroup.getListOfUser().add(currentUser);
            System.out.println("You're added to the group.");
        }
        else
            System.out.println("Invalid invite code or group doesnt exist!.");
    }

    public void addMemberPublicGroupByMember(User currentUser, User invitedUser,int groupID)throws NoSuchFieldException,IllegalAccessException
    {
        PublicGroup publicGroup = groupRepository.getGroupByID(groupID);
        if(publicGroup!= null)
        {
            if(checkMemberGroup(currentUser,groupID,PublicGroup.class))
            {
                if(checkMemberGroup(invitedUser,groupID,PublicGroup.class))
                {
                    System.out.println("Your friend is already a member");
                }
                else {
                    publicGroup.getListOfUser().add(invitedUser);
                    groupRepository.update(publicGroup,groupRepository.getIndexOfObject(publicGroup));
                }
            }
            else
            {
                System.out.println("You're not a member");
            }
        }
        else
            System.out.println("Group does not exist!");


    }
    private boolean checkAdminPrivateGroup(User admin, int groupID)throws NoSuchFieldException,IllegalAccessException
    {
        PrivateGroup privateGroup = groupRepository.getPrivateGroupByID(groupID);
        if(privateGroup!=null)
        {
            if(privateGroup.getAdminGroup().contains(admin))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public  <T> boolean checkMemberGroup(User user,int groupID,Class<T> entityClass)throws NoSuchFieldException,IllegalAccessException
    {
        if(entityClass.equals(PublicGroup.class)) {
            PublicGroup publicGroup = groupRepository.getGroupByID(groupID);
            if (publicGroup != null) {
                if (publicGroup.getListOfUser().contains(user)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else if(entityClass.equals(PrivateGroup.class)) {
            PrivateGroup privateGroup = groupRepository.getPrivateGroupByID(groupID);
            if (privateGroup != null) {
                if (privateGroup.getListOfUser().contains(user)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else
            throw new InputMismatchException("Please input correct type");
    }

    private boolean checkGroupExistByInviteCode(String inviteCode) throws NoSuchFieldException,IllegalAccessException
    {
        if(groupRepository.getGroupByInviteCode(inviteCode)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }




}
