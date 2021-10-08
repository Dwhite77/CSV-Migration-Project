package com.sparta.employee;

import java.sql.*;
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

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.next();
        System.out.println(resultSet.toString());
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

//        System.out.println("EmployeeID: "+EmpID);
//        System.out.println("Name: "+ prefix+firstName+" "+ middleInital+" "+ lastName);
//        System.out.println("Gender: "+ gender);
//        System.out.println("Email: "+ email);
//        System.out.println("DOB: " +dateOB);
//        System.out.println("Date of Employment: "+dateOE);
//        System.out.println("Salary: "+salary);


        }

}
// in here I want to use a lambda expression to create some sql commands as I think this will be a good use for them