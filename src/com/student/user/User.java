package com.student.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
    private int id=0;
    private String lastName;
    private String firstName;
    private String fullName;
    private String username;
    private String password;
    private String gender;
    private String dateOfBirth;
    private List<Integer> sentMessages;
    private List<Integer> sentFiles;
    private static int count = 0;

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

    public User(String lastName, String firstName, String fullName, String username, String password, String gender, String dateOfBirth) {
        id = count++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.sentFiles = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
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
