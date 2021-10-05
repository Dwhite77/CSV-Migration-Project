package com.sparta.employee;

import java.sql.Date;

public class EmployeeObject {
    private int employeeID;
    private String prefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private Date dateOfBirth;
    private Date dateOfEmployment;
    private int salary;

    public EmployeeObject(String employeeID, String prefix, String firstName, String middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfEmployment, String salary) {
        setEmployeeID(employeeID);
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        setDateOfBirth(dateOfBirth); // little bit janky but it should work
        setDateOfEmployment(dateOfEmployment);
        setSalary(salary);
    }
    public EmployeeObject(){

    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        int employeeIDOut = Integer.valueOf(employeeID);
        this.employeeID = employeeIDOut;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        String[] arrayDOB = null;
        String year = null;
        String month = null;
        String day = null;

        arrayDOB = dateOfBirth.split("/");

        year = arrayDOB[2];
        month = arrayDOB[0];
        day = arrayDOB[1];

        String output = year + "-" + month + "-" +day;

        Date convertedDOB = Date.valueOf(output);
        this.dateOfBirth = convertedDOB;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        String[] arrayDOE = null;
        String year = null;
        String month = null;
        String day = null;

        arrayDOE = dateOfEmployment.split("/");

        year = arrayDOE[2];
        month = arrayDOE[0];
        day = arrayDOE[1];

        String output = year + "-" + month + "-" +day;

        Date convertedDOE = Date.valueOf(output);
        this.dateOfEmployment = convertedDOE;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        int salaryOut = Integer.valueOf(salary);
        this.salary = salaryOut;
    }
}
