package com.student.group;

import com.student.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PublicGroup extends Group {
    private static List<User>listOfUser = new ArrayList<User>();
    private UUID idGroup;

    public PublicGroup(UUID idGroup) {
        idGroup = UUID.randomUUID();
    }
}
