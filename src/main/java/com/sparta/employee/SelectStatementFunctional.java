package com.sparta.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectStatementFunctional {
    public static void getEmployeeByID(int employeeID){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE EMPLOYEE_ID = ?"); // i want to add another field in here to say whether autocommit is on or off
            prepState.setString(1, String.valueOf(employeeID));
            prepState.execute();
            prepState.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
// in here I want to use a lambda expression to create some sql commands as I think this will be a good use for them