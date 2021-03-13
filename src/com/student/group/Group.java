package com.student.group;

import com.student.file.File;
import com.student.message.Message;
import com.student.user.User;

import java.util.List;
import java.util.UUID;

public abstract class Group {
    private List<User>listOfUser;
    public List<Message> groupMessages;
    public List<File> groupFiles;


}
