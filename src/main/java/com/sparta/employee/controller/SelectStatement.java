package com.sparta.employee.controller;

import com.sparta.employee.view.LoggingMain;
import com.sparta.employee.view.WriteResultSet;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Scanner;


public class SelectStatement {
    private ResultSet resultSet;
    private static Logger log = LoggingMain.getLogger();
    public ResultSet getResultSet() {
        return resultSet;
    }
    private WriteResultSet wS = new WriteResultSet();
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void getEmployeeByID(int employeeID){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE EMPLOYEE_ID = ?");
            prepState.setString(1, String.valueOf(employeeID));
            setResultSet(prepState.executeQuery());
            wS.writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getEmployeeByFirstName(String firstName){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE FIRST_NAME = ?");
            prepState.setString(1,firstName );
            setResultSet(prepState.executeQuery());
            wS.writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getEmployeeByLastName(String lastName){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
            PreparedStatement prepState = conn.prepareStatement("SELECT * FROM EMPLOYEE_DATABASE.EMPLOYEE_TABLE WHERE LAST_NAME = ?");
            prepState.setString(1,lastName);
            setResultSet(prepState.executeQuery());
            wS.writeResultSet(getResultSet());
            prepState.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                try{
                    String answer3 = scn.next();
                    sS.getEmployeeByFirstName(answer3);
                } catch (Exception e) {
                    System.err.println("Not a Valid input");
                    log.error("Not a valid input");
                }
                return true;
            }
            if(answer2.equals("lastname")||answer2.equals("l")||answer2.equals("last")){
                System.out.println("What last name would you like to search for?");
                try{
                    String answer3 = scn.next();
                    sS.getEmployeeByLastName(answer3);
                } catch (Exception e) {
                    System.err.println("Not a Valid input");
                    log.error("Not a valid input");
                }
                return true;
            }
            if(answer2.equals("employeeid")||answer2.equals("eid")|| answer2.equals("id")){
                System.out.println("Which employee number would you like?");
                try{
                    int answer3 = scn.nextInt();
                    sS.getEmployeeByID(answer3);
                } catch (Exception e) {
                    System.err.println("Not a Valid input");
                    log.error("Not a valid input");
                }
            }
            else
                System.err.println("Enter a valid option"); log.error("Not a valid option");;
            return true;
        }
        else return false;
    }



}
