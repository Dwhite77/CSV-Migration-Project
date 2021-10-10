package com.sparta.employee;

import com.sparta.employee.model.EmployeeObject;
import com.sparta.outdated.DatabaseCreationSQLite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDataBaseCreation {

    @Test
    public void testCreateATable(){
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        DatabaseCreationSQLite dBCreation = new DatabaseCreationSQLite();
        dBCreation.writeToDB(employeeArrayList);
    }

}
