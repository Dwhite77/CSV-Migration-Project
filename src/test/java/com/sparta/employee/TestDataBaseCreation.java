package com.sparta.employee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDataBaseCreation {

    @Test
    public void testCreateATable(){
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        DatabaseCreation.writeToDB(employeeArrayList);
    }

}
