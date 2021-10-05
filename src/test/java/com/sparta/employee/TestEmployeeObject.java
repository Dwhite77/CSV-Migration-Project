package com.sparta.employee;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployeeObject {

    @Test
    public void givenStringInTheFormatOfMDYSwapToYMDDOB(){
        EmployeeObject employee1 = new EmployeeObject();
        String testString = "2/8/1970";
        Date testDOB = Date.valueOf("1970-2-8");
        employee1.setDateOfBirth(testString);
        assertEquals(testDOB,employee1.getDateOfBirth());
    }


    @Test
    public void givenStringInTheFormatOfMDYSwapToYMDDOE(){
        EmployeeObject employee1 = new EmployeeObject();
        String testString = "2/8/1970";
        Date testDOE = Date.valueOf("1970-2-8");
        employee1.setDateOfEmployment(testString);
        assertEquals(testDOE,employee1.getDateOfEmployment());
    }
}
