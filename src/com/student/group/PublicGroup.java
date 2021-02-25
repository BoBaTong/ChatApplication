package com.student.group;

import com.student.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PublicGroup extends Group {
    private static List<User>listOfUser = new ArrayList<User>();
    private List<User> adminGroup;
    private UUID inviteCode;

    public PublicGroup(User user) {
        inviteCode = UUID.randomUUID();
        adminGroup.add(user);

    }

    public UUID getInviteCode() {
        return inviteCode;
    }

}
