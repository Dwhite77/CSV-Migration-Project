package com.sparta.employee;

import com.sparta.employee.model.EmployeeMain;
import com.sparta.employee.model.EmployeeObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.text.ParseException;

public class TestEmployeeMain {
    @Test // this data has already been sanitised before it gets here so there wont be any invalid data at this point
    public void givenAValidStringArrayReturnValidEmployeeObject() throws ParseException {
        EmployeeObject testEO = new EmployeeObject(123,"mr.", "dan", "d","white","M","dan1@mail.com", Date.valueOf("1900-1-1"),Date.valueOf("1900-1-1"),123456);
        String[] testStrArr = {"123","mr.","dan","d","white","M","dan1@mail.com","1900-1-1","1900-1-1","123456"};
        assertEquals(testEO, EmployeeMain.stringArrToEmployee(testStrArr));
    }

}
