package com.student.services;

import com.student.file.File;
import com.student.group.PublicGroup;
import com.student.message.Message;
import com.student.repository.GroupRepository;
import com.student.repository.UserRepository;
import com.student.user.User;

import java.util.ArrayList;
import java.util.List;


public class MessageServices {

    GroupRepository groupRepository = new GroupRepository();
    UserRepository userRepository = new UserRepository();
    GroupServices groupServices = new GroupServices();
    public void sendMessage(Message message,int sendID,int receiveID) throws NoSuchFieldException, IllegalAccessException {
        groupRepository.getGroupByID(receiveID).groupMessages.add(message);

        User user = userRepository.getUserByID(sendID);
        userRepository.getUserByID(sendID).getSentMessages().add(message.getId());
    }

    public void sendFile(File file,int receiveID,int sendID)
    {

    }

    public void showMessagePublicGroup(int userID, PublicGroup publicGroup) throws NoSuchFieldException, IllegalAccessException {
        List<Message> messageList = new ArrayList<>();
        User user = userRepository.getUserByID(userID);
        if(groupServices.checkMemberGroup(user,publicGroup.getId(),PublicGroup.class))
        {
            for (int i = publicGroup.groupMessages.size(); i >0 ; i--) {
                System.out.println(publicGroup.groupMessages.get(i-1).getContent());
            }
        }



    }
}
