package com.student.group;

import com.student.user.User;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group {
    private static List<User> listOfUser = new ArrayList<User>();

    private List<User> adminGroup;
}
