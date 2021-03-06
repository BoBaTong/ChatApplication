package com.student.services;

import com.student.file.File;
import com.student.group.Group;
import com.student.message.Message;
import com.student.repository.GroupRepository;
import com.student.repository.UserRepository;
import com.student.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MessageServices {

    GroupRepository groupRepository = new GroupRepository();
    UserRepository userRepository = new UserRepository();
    GroupServices groupServices = new GroupServices();


    /**
     * @param sendID User id
     * @param receiveID Group id
     */
    public void sendMessageToGroup(Message message, int sendID, int receiveID)
            throws NoSuchFieldException, IllegalAccessException {
        groupRepository.getGroupByID(receiveID).groupMessages.add(message);
        userRepository.getUserByID(sendID).getSentMessages().add(message.getId());
        //update group and user to DB
    }
    /**
     * @param sendID User who sent id
     * @param receiveID User who received id
     */
    public void sendMessageToUser(Message message, int sendID, int receiveID)
            throws NoSuchFieldException, IllegalAccessException {
        userRepository.getUserByID(receiveID).getReceivedMessages().add(message);
        userRepository.getUserByID(sendID).getSentMessages().add(message.getId());
        //update group and user to DB

    }

    public void sendFileToGroup(File file, int receiveID, int sendID)
            throws NoSuchFieldException, IllegalAccessException {
        groupRepository.getGroupByID(receiveID).groupFiles.add(file);
        userRepository.getUserByID(sendID).getSentFiles().add(file.getId());
        //update
    }


    public void sendFileToUser(File file, int receiveID, int sendID)
            throws NoSuchFieldException, IllegalAccessException {
        userRepository.getUserByID(sendID).getSentFiles().add(file.getId());
        userRepository.getUserByID(receiveID).getReceivedFiles().add(file);
        //update
    }


    /**
     * @return a list with all messages that contain in group
     * */
    public List<Message> showAllMessageGroup(int userID, int groupID)
            throws NoSuchFieldException, IllegalAccessException {
        List<Message> messageList = new ArrayList<Message>();
        User user = userRepository.getUserByID(userID);
        Group group = groupRepository.getGroupByID(groupID);
        if (groupServices.checkMemberGroup(user, group.getId())) {
            for (int i = group.groupMessages.size(); i > 0; i--) {
                messageList.add(group.groupMessages.get(i - 1));
            }
        }
        return messageList;
    }


    /**
     * @return a list with all messages that message id in sent user equals with the id contains in received messages in
     * received user
     * */
    public List<Message> showAllMessageUser(int userID, int user2ID)
            throws NoSuchFieldException, IllegalAccessException {
        List<Message> messageList = new ArrayList<Message>();
        User user1 = userRepository.getUserByID(userID);
        User user2 = userRepository.getUserByID(user2ID);
        for (int messageID : user1.getSentMessages()) {
            for (int i = user2.getReceivedMessages().size(); i > 0; i--) {
                if(messageID==user2.getReceivedMessages().get(i - 1).getId()) {
                    messageList.add(user2.getReceivedMessages().get(i - 1));
                }
            }
        }
        for (int messageID : user2.getSentMessages()) {
            for (int i = user1.getReceivedMessages().size(); i > 0; i--) {
                if(messageID==user1.getReceivedMessages().get(i - 1).getId()) {
                    messageList.add(user2.getReceivedMessages().get(i - 1));
                }
            }
        }
        messageList.sort((o1, o2) -> o2.getId() - o1.getId());
        return messageList;
    }
    public List<Message> showLatestMessageGroup(int userID, int groupID, int k)
            throws NoSuchFieldException, IllegalAccessException {
        User user = userRepository.getUserByID(userID);
        Group group = groupRepository.getGroupByID(groupID);
        List<Message> messageList = new ArrayList<Message>();
        if (groupServices.checkMemberGroup(user, group.getId())) {
            for (int i = group.groupMessages.size(); i > 0 && k>0; i--,k--) {
                messageList.add(group.groupMessages.get(i - 1));
            }
        }
        return messageList;
    }

    public List<Message> showLatestMessageGroupExceptM(int userID,int groupID,int k,int m)
            throws NoSuchFieldException, IllegalAccessException {
        List<Message> messageList = showLatestMessageGroup(userID,groupID,k);
        List<Message> messageListReturn = new ArrayList<>();
        for (int i = m; i < k ; i++) {
            messageListReturn.add(messageList.get(i));
        }
        return messageListReturn;
    }

    //showMessagePrivateGroup same as showMessagePublicGroup
    //showMessageUser same as showMessagePublicGroup
    public List<File> showAllFileInGroup(User user, Group group) {
        List<File> fileList = new ArrayList<File>();
        for (Integer id : user.getSentFiles()) {
            for (File file : group.groupFiles)
            {
                if(id.equals(file.getId()))
                {
                    fileList.add(file);
                }
            }
        }
        return fileList;

    }

    public List<File> showAllFileUser(int userID, int user2ID)
            throws NoSuchFieldException, IllegalAccessException {
        List<File> fileList = new ArrayList<>();
        User user1 = userRepository.getUserByID(userID);
        User user2 = userRepository.getUserByID(user2ID);
        for (int messageID : user1.getSentFiles()) {
            for (int i = user2.getReceivedFiles().size(); i > 0; i--) {
                if(messageID==user2.getReceivedFiles().get(i - 1).getId()) {
                    fileList.add(user2.getReceivedFiles().get(i - 1));
                }
            }
        }
        for (int messageID : user2.getSentFiles()) {
            for (int i = user1.getReceivedFiles().size(); i > 0; i--) {
                if(messageID==user1.getReceivedFiles().get(i - 1).getId()) {
                    fileList.add(user1.getReceivedFiles().get(i - 1));
                }
            }
        }
        fileList.sort(((o1, o2) -> o2.getId()-o1.getId()));
        return fileList;
    }
    //showAllFilePrivateGroup same as showAllFilePublicGroup

    public void deleteMessageInGroup(Group group,User user,Message message)
    {
        if(group.groupMessages.contains(message) && user.getSentMessages().contains(message.getId()))
        {
            group.groupMessages.remove(message);
            user.getSentMessages().remove((Integer) message.getId());
            //update
        }
    }

    public void deleteFileInGroup(Group group,User user,File file)
    {
        if(group.groupFiles.contains(file) && user.getSentFiles().contains(file.getId()))
        {
            group.groupFiles.remove(file);
            user.getSentFiles().remove((Integer) file.getId());
            //update
        }
    }

    public void deleteMessageUser(User userSent,User userReceived,Message message)
    {
        if(userSent.getSentMessages().contains(message.getId())
        && userReceived.getReceivedMessages().contains(message))
        {
            userReceived.getReceivedMessages().remove(message);
            userSent.getSentMessages().remove((Integer) message.getId());
            //update
        }
    }

    public void deleteFileUser(User userSent,User userReceived,File file)
    {
        if(userSent.getSentFiles().contains(file.getId())
        && userReceived.getReceivedFiles().contains(file))
        {
            userReceived.getReceivedFiles().remove(file);
            userSent.getSentFiles().remove((Integer) file.getId());
            //update
        }
    }

    public List<Message> findMessageByKeywordInGroup(String words,Group group)
    {
        List<Message> messageList = new ArrayList<Message>();
        for (Message message : group.groupMessages)
        {
            if(message.getContent().toLowerCase().contains(words.toLowerCase()))
            {
                messageList.add(message);
            }
        }
        return messageList;
    }
    public List<Message> findMessageByKeywordInUser(String words,User user1,User user2)
    {
        List<Message> messageList = new ArrayList<Message>();
        for (Message message : user1.getReceivedMessages())
        {
            if(message.getContent().toLowerCase().contains(words.toLowerCase()))
            {
                messageList.add(message);
            }
        }
        for (Message message : user2.getReceivedMessages())
        {
            if(message.getContent().toLowerCase().contains(words.toLowerCase()))
            {
                messageList.add(message);
            }
        }
        return messageList;
    }

}
