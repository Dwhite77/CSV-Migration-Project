package com.sparta.employee;

import java.sql.*;
import java.util.ArrayList;

public class DBCreationMYSQL {

    public void writeToDB(ArrayList<EmployeeObject> empArrList) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            try{
                statement.executeUpdate("DROP TABLE EMPLOYEE_DATABASE.EMPLOYEE_TABLE");
            } catch (SQLException throwables) {
                System.out.println("No TABLE, one sec im making it");
            }

            statement.executeUpdate("CREATE TABLE EMPLOYEE_DATABASE.EMPLOYEE_TABLE (EMPLOYEE_ID INTEGER NOT NULL, PREFIX VARCHAR(100), FIRST_NAME VARCHAR(100),MIDDLE_INITIAL VARCHAR(100), " +
                    "LAST_NAME VARCHAR(100), GENDER VARCHAR(100) ,EMAIL VARCHAR(100) , DATE_OF_BIRTH DATE, DATE_OF_EMPLOYMENT DATE, SALARY INTEGER, PRIMARY KEY (EMPLOYEE_ID))"); //"UID" INTEGER PRIMARY KEY AUTOINCREMENT,
            statement.close();
            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    public static void duplicatesToDB(ArrayList<EmployeeObject> empArrList) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE EMPLOYEE_DUPLICATES_TABLE");
            statement.executeUpdate("CREATE TABLE EMPLOYEE_DUPLICATES_DATABASE (UID INTEGER PRIMARY KEY AUTOINCREMENT, EMPLOYEE_ID INTEGER NOT NULL, PREFIX VARCHAR(100), FIRST_NAME VARCHAR(100),MIDDLE_INITIAL VARCHAR(100), " +
                    "LAST_NAME VARCHAR(100), GENDER VARCHAR(100) ,EMAIL VARCHAR(100) , DATE_OF_BIRTH DATE, DATE_OF_EMPLOYMENT DATE, SALARY INTEGER)");
            statement.close();

            if (!empArrList.isEmpty()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEE_DUPLICATES_DATABASE(EMPLOYEE_ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME,GENDER,EMAIL,DATE_OF_BIRTH,DATE_OF_EMPLOYMENT,SALARY)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)");
                for (int i = 0; i < empArrList.size(); i++) {
                    preparedStatement.setString(1, String.valueOf(empArrList.get(i).getEmployeeID()));
                    preparedStatement.setString(2, empArrList.get(i).getPrefix());
                    preparedStatement.setString(3, empArrList.get(i).getFirstName());
                    preparedStatement.setString(4, empArrList.get(i).getMiddleInitial());
                    preparedStatement.setString(5, empArrList.get(i).getLastName());
                    preparedStatement.setString(6, empArrList.get(i).getGender());
                    preparedStatement.setString(7, empArrList.get(i).getEmail());
                    preparedStatement.setString(8, String.valueOf(empArrList.get(i).getDateOfBirth()));
                    preparedStatement.setString(9, String.valueOf(empArrList.get(i).getDateOfEmployment()));
                    preparedStatement.setString(10, String.valueOf(empArrList.get(i).getSalary()));
                    preparedStatement.execute();
                }
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
