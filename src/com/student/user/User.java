package com.student.user;

import com.student.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    //id and count to fake auto-increased id
    private int id=0;
    private static int count = 0;
    private String lastName;
    private String firstName;
    private String fullName;
    private String username;
    private String password;
    private String gender;
    private String dateOfBirth;
    private List<Message> receivedMessages;
    private Map<User,String> alias;
    private List<Integer> sentMessages;
    private List<Integer> sentFiles;


    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public Map<User, String> getAlias() {
        return alias;
    }

    public void setAlias(Map<User, String> alias) {
        this.alias = alias;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }



    public List<Integer> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Integer> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Integer> getSentFiles() {
        return sentFiles;
    }

    public void setSentFiles(List<Integer> sentFiles) {
        this.sentFiles = sentFiles;
    }

//    public User(String lastName, String firstName, String fullName, String username, String password, String gender, String dateOfBirth) {
//        id = count++;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.fullName = fullName;
//        this.username = username;
//        this.password = password;
//        this.sentFiles = new ArrayList<>();
//        this.sentMessages = new ArrayList<>();
//        this.receivedMessage = new ArrayList<>();
//    }


    public User(String lastName, String firstName, String fullName, String username, String password) {
        id = ++count;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.sentFiles = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        this.alias = new HashMap<User,String>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
