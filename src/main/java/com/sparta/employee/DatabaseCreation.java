package com.sparta.employee;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseCreation{
    public void writeToDB(ArrayList<EmployeeObject> empArrList) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:week5DB.db")) {
            Statement statement = conn.createStatement();
            conn.setAutoCommit(false);

            statement.executeUpdate("DROP TABLE \"EMPLOYEE_DATABASE\""); // not sure if this will work from the first running, as i think it will throw an error and quit, due to there not being an employeedatabase to drop
            // but it is what cathy recommended so im going to stick with it

            statement.executeUpdate("CREATE TABLE \"EMPLOYEE_DATABASE\" (\"EMPLOYEE_ID\" INTEGER NOT NULL, \"PREFIX\" VARCHAR(100), \"FIRST_NAME\" VARCHAR(100),\"MIDDLE_INITIAL\" VARCHAR(100), " +
                    "\"LAST_NAME\" VARCHAR(100), \"GENDER\" VARCHAR(100) ,\"EMAIL\" VARCHAR(100) , \"DATE_OF_BIRTH\" DATE, \"DATE_OF_EMPLOYMENT\" DATE, \"SALARY\" INTEGER, PRIMARY KEY (\"EMPLOYEE_ID\"))"); //"UID" INTEGER PRIMARY KEY AUTOINCREMENT,
            statement.close();

            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
//
//    public void insertStatements(PreparedStatement ps, ArrayList<EmployeeObject> empArrList, int lowerBounds,int upperBounds) throws SQLException {
//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:week5DB.db")) {
//            if (!empArrList.isEmpty()) {
//                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEE_DATABASE(EMPLOYEE_ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME,GENDER,EMAIL,DATE_OF_BIRTH,DATE_OF_EMPLOYMENT,SALARY)"
//                        + "VALUES(?,?,?,?,?,?,?,?,?,?)");
//
//                for (int i = lowerBounds; i < upperBounds; i++) {
//                    ps.setString(1, String.valueOf(empArrList.get(i).getEmployeeID()));
//                    ps.setString(2, empArrList.get(i).getPrefix());
//                    ps.setString(3, empArrList.get(i).getFirstName());
//                    ps.setString(4, empArrList.get(i).getMiddleInitial());
//                    ps.setString(5, empArrList.get(i).getLastName());
//                    ps.setString(6, empArrList.get(i).getGender());
//                    ps.setString(7, empArrList.get(i).getEmail());
//                    ps.setString(8, String.valueOf(empArrList.get(i).getDateOfBirth()));
//                    ps.setString(9, String.valueOf(empArrList.get(i).getDateOfEmployment()));
//                    ps.setString(10, String.valueOf(empArrList.get(i).getSalary()));
//                    ps.execute();
//                }
//
//                System.out.println("Done");
//
//            }
//        }
//        catch (SQLException sqle){
//            sqle.printStackTrace();
//        }
//    }

    public static void duplicatesToDB(ArrayList<EmployeeObject> empArrList) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:week5DB.db")) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE \"EMPLOYEE_DUPLICATES_DATABASE\"");
            statement.executeUpdate("CREATE TABLE \"EMPLOYEE_DUPLICATES_DATABASE\" (\"UID\" INTEGER PRIMARY KEY AUTOINCREMENT, \"EMPLOYEE_ID\" INTEGER NOT NULL, \"PREFIX\" VARCHAR(100), \"FIRST_NAME\" VARCHAR(100),\"MIDDLE_INITIAL\" VARCHAR(100), " +
                    "\"LAST_NAME\" VARCHAR(100), \"GENDER\" VARCHAR(100) ,\"EMAIL\" VARCHAR(100) , \"DATE_OF_BIRTH\" DATE, \"DATE_OF_EMPLOYMENT\" DATE, \"SALARY\" INTEGER)");
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
