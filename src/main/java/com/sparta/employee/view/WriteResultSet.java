package com.sparta.employee.view;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WriteResultSet {
    private static Logger log = LoggingMain.getLogger();

    public static void writeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int count =0;
        while(resultSet.next()) {

            String EmpID = resultSet.getString(1);
            String prefix = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String middleInitial = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String email = resultSet.getString(7);
            Date dateOB = resultSet.getDate(8);
            Date dateOE = resultSet.getDate(9);
            String salary = resultSet.getString(10);


            System.out.println("EmployeeID: " + EmpID);
            System.out.println("Name: " + prefix + " " + firstName + " " + middleInitial + " " + lastName);
            System.out.println("Gender: " + gender);
            System.out.println("Email: " + email);
            System.out.println("DOB: " + dateOB);
            System.out.println("Date of Employment: " + dateOE);
            System.out.println("Salary: " + salary);
            System.out.println("-----------------");
            count++;
        }
        if(count == 0){
            log.info("No search results found");
            System.err.println("No results for this query");
        }

    }
}
