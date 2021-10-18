package com.sparta.employee.model;

import java.sql.*;
import java.util.ArrayList;

public class DBCreationMYSQL {

    public void createTableInDB() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=,port=,user=,password=)")) {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            try{
                statement.executeUpdate("DROP TABLE EMPLOYEE_DATABASE.EMPLOYEE_TABLE");
            } catch (SQLException throwables) {
                System.out.println("No Table for employees, I will create it now");
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
            conn.setAutoCommit(false);
            try{
                statement.executeUpdate("DROP TABLE EMPLOYEE_DATABASE.EMPLOYEE_DUPLICATES_TABLE");
            }catch (SQLException throwables){
                System.out.println("No Table for duplicates, I will create it now");
            }

            statement.executeUpdate("CREATE TABLE EMPLOYEE_DATABASE.EMPLOYEE_DUPLICATES_TABLE (UID INTEGER NOT NULL AUTO_INCREMENT, EMPLOYEE_ID INTEGER NOT NULL, PREFIX VARCHAR(100), FIRST_NAME VARCHAR(100),MIDDLE_INITIAL VARCHAR(100), " +
                    "LAST_NAME VARCHAR(100), GENDER VARCHAR(100) ,EMAIL VARCHAR(100) , DATE_OF_BIRTH DATE, DATE_OF_EMPLOYMENT DATE, SALARY INTEGER, PRIMARY KEY (UID))");
            statement.close();

            if (!empArrList.isEmpty()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEE_DATABASE.EMPLOYEE_DUPLICATES_TABLE (EMPLOYEE_ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME,GENDER,EMAIL,DATE_OF_BIRTH,DATE_OF_EMPLOYMENT,SALARY)"
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
                conn.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createTimeDB() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            try{
                statement.executeUpdate("DROP TABLE EMPLOYEE_DATABASE.EMPLOYEE_TABLE_TIMINGS");
            } catch (SQLException throwables) {
                System.out.println("No Table for timings, one sec im making it");
            }

            statement.executeUpdate("CREATE TABLE EMPLOYEE_DATABASE.EMPLOYEE_TABLE_TIMINGS (RUNID INTEGER NOT NULL AUTO_INCREMENT, TIME INTEGER NOT NULL, THREADS INTEGER NOT NULL, PRIMARY KEY (RUNID))"); // INTEGER PRIMARY KEY AUTOINCREMENT,
            statement.close();
            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    public static void writeTimeTODB(int time, int threads){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            conn.setAutoCommit(false);
            PreparedStatement prepState = conn.prepareStatement("INSERT INTO EMPLOYEE_DATABASE.EMPLOYEE_TABLE_TIMINGS ( TIME, THREADS)"+"VALUES(?,?)"); // i want to add another field in here to say whether autocommit is on or off
            prepState.setString(1, String.valueOf(time));
            prepState.setString(2, String.valueOf(threads));
            prepState.execute();
            prepState.close();
            conn.commit();
            System.out.println("Finished Writing Times");
        } catch (SQLException sqle) {
            createTimeDB();
            writeTimeTODB(time,threads);
        }
    }

}
