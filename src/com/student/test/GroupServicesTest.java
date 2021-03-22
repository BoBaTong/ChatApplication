package com.student.test;

import com.student.DataStorage;
import com.student.group.Group;
import com.student.group.PrivateGroup;
import com.student.group.PublicGroup;
import com.student.services.GroupServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

public class GroupServicesTest {
    GroupServices groupServices;
    DataStorage dataStorage;
    Group group;

    @BeforeEach
    void setUp() throws Exception{
        dataStorage = DataStorage.getInstance();
        groupServices = new GroupServices();
    }

    @Test
    public void testCreateGroup() throws NoSuchFieldException,IllegalAccessException
    {
        String res = groupServices.createGroup(0, 1);
        Assertions.assertEquals("Private group created successful!", res);

        groupServices.createGroup(1, 2);

        try{
            groupServices.createGroup(2, 2);
        }catch(InputMismatchException e){
            Assertions.assertEquals("Please input correctly type", e.getMessage());
        }

        int n = dataStorage.getGroupCollection().size();
        Assertions.assertEquals(2, n);

        group = dataStorage.getGroupCollection().get(1);
        Assertions.assertEquals(PublicGroup.class, group.getClass());
    }
}
