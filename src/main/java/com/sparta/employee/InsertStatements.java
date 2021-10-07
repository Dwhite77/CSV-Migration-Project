package com.sparta.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertStatements implements Runnable{


    private ArrayList<EmployeeObject> empArrList;
    private int lowerBounds;
    private int upperBounds;
    private int whichInstance;

    public InsertStatements( ArrayList<EmployeeObject> empArrList, int lowerBounds, int upperBounds, int whichInstance) {
        this.whichInstance = whichInstance;
        this.empArrList = empArrList;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }



    public ArrayList<EmployeeObject> getEmpArrList() {
        return empArrList;
    }

    public void setEmpArrList(ArrayList<EmployeeObject> empArrList) {
        this.empArrList = empArrList;
    }

    public int getLowerBounds() {
        return lowerBounds;
    }

    public void setLowerBounds(int lowerBounds) {
        this.lowerBounds = lowerBounds;
    }

    public int getUpperBounds() {
        return upperBounds;
    }

    public void setUpperBounds(int upperBounds) {
        this.upperBounds = upperBounds;
    }

    public int getWhichInstance() {
        return whichInstance;
    }

    public void insertStatements() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://(host=DESKTOP-QUISOMI,port=3306,user=Dwhite,password=plM45!bn)")) {
//            conn.setAutoCommit(false);
            if (!empArrList.isEmpty()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEE_DATABASE.EMPLOYEE_TABLE(EMPLOYEE_ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME,GENDER,EMAIL,DATE_OF_BIRTH,DATE_OF_EMPLOYMENT,SALARY)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)");

                for (int i = getLowerBounds(); i < getUpperBounds(); i++) {
                    preparedStatement.setString(1, String.valueOf(getEmpArrList().get(i).getEmployeeID()));
                    preparedStatement.setString(2, getEmpArrList().get(i).getPrefix());
                    preparedStatement.setString(3, getEmpArrList().get(i).getFirstName());
                    preparedStatement.setString(4, getEmpArrList().get(i).getMiddleInitial());
                    preparedStatement.setString(5, getEmpArrList().get(i).getLastName());
                    preparedStatement.setString(6, getEmpArrList().get(i).getGender());
                    preparedStatement.setString(7, getEmpArrList().get(i).getEmail());
                    preparedStatement.setString(8, String.valueOf(getEmpArrList().get(i).getDateOfBirth()));
                    preparedStatement.setString(9, String.valueOf(getEmpArrList().get(i).getDateOfEmployment()));
                    preparedStatement.setString(10, String.valueOf(getEmpArrList().get(i).getSalary()));
                    preparedStatement.execute();
                }
                preparedStatement.close();
                System.out.println("Done: "+getWhichInstance());
//                conn.commit();
            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            insertStatements();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
