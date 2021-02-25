package com.student.services;

import com.student.group.PrivateGroup;
import com.student.group.PublicGroup;
import com.student.repository.GroupRepository;
import com.student.user.User;

import java.util.InputMismatchException;

public class GroupServices {

    GroupRepository groupRepository = new GroupRepository();


    public void createGroup(int groupSelection, User currentUser)
    {
        if(groupSelection==0)
        {
            PrivateGroup privateGroup = new PrivateGroup(currentUser);
            groupRepository.save(privateGroup);
            System.out.println("Private group created successful!");
        }
        else if(groupSelection==1)
        {
            PublicGroup publicGroup = new PublicGroup(currentUser);
            groupRepository.save(publicGroup);
            System.out.println("Public group created successful!, invite code: "+publicGroup.getInviteCode());
        }
        else
            throw new InputMismatchException("Please input correctly type");
    }

    //create groupPublic
    //create groupPrivate

    //join (code)
    //invite (addAnotherUser)
    //addMemberPublic (code and invite)


    //addMemberPrivate (admin??? -> list)

}
