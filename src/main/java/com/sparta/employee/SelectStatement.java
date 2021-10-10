package com.sparta.employee;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class SelectStatement {
    private ResultSet resultSet;

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void getEmployeeByID(int employeeID){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE EMPLOYEE_ID = ?"); // i want to add another field in here to say whether autocommit is on or off
            prepState.setString(1, String.valueOf(employeeID));
            setResultSet(prepState.executeQuery());
            writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getEmployeeByFirstName(String firstName){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE FIRST_NAME = ?"); // i want to add another field in here to say whether autocommit is on or off
            prepState.setString(1,firstName );
            setResultSet(prepState.executeQuery());
            writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getEmployeeByLastName(String lastName){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE LAST_NAME = ?"); // i want to add another field in here to say whether autocommit is on or off
            prepState.setString(1,lastName);
            setResultSet(prepState.executeQuery());
            writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void writeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.next();
        while(resultSet.next()) {
            String EmpID = resultSet.getString(1);
            String prefix = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String middleInital = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String email = resultSet.getString(7);
            Date dateOB = resultSet.getDate(8);
            Date dateOE = resultSet.getDate(9);
            String salary = resultSet.getString(10);


            System.out.println("EmployeeID: " + EmpID);
            System.out.println("Name: " + prefix + " " + firstName + " " + middleInital + " " + lastName);
            System.out.println("Gender: " + gender);
            System.out.println("Email: " + email);
            System.out.println("DOB: " + dateOB);
            System.out.println("Date of Employment: " + dateOE);
            System.out.println("Salary: " + salary);
            System.out.println("-----------------");
        }

    }

    public static boolean databaseReading(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Would you like to search for a record?");
        String answer1 = scn.next().toLowerCase();
        SelectStatement sS = new SelectStatement();
        if(answer1.equals("yes") || answer1.equals("y")){
            System.out.println("How would you like to search for an employee? First Name (f), Last Name(l), or Employee ID(id)?");
            String answer2 = scn.next().toLowerCase();
            if(answer2.equals("firstname") || answer2.equals("f")||  answer2.equals("first")){
                System.out.println("What first name would you like to search for?");
                String answer3 = scn.next();
                try{sS.getEmployeeByFirstName(answer3);} catch (Exception e) {e.printStackTrace();}
                return true;
            }
            if(answer2.equals("lastname")||answer2.equals("l")||answer2.equals("last")){
                System.out.println("What last name would you like to search for?");
                String answer3 = scn.next();
                try{sS.getEmployeeByLastName(answer3);} catch (Exception e) {e.printStackTrace();}
                return true;
            }
            if(answer2.equals("employeeid")||answer2.equals("eid")|| answer2.equals("id")){
                System.out.println("Which employee number would you like?");
                int answer3 = scn.nextInt();
                try{sS.getEmployeeByID(answer3);}catch (Exception e) {e.printStackTrace();}
                return true;
            }
            else
                System.err.println("Enter a valid option");
            return true;
        }
        else return false;
    }



}
// in here I want to use a lambda expression to create some sql commands as I think this will be a good use for them