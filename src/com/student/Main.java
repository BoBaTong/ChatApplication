package com.student;


import com.student.group.PrivateGroup;
import com.student.services.UserServices;
import com.student.user.User;

public class Main {

    public static void main(String[] args) {
        UserServices userServices = new UserServices();
        String username = "s";
        userServices.addUser(username,"s","s","s","s","s","s");
        System.out.println("binh dep trai");
    }
}
