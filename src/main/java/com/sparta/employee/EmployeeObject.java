package com.sparta.employee;

import java.sql.Date;
import java.util.Objects;

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

    public EmployeeObject(int employeeID, String prefix, String firstName, String middleInitial, String lastName, String gender, String email, Date dateOfBirth, Date dateOfEmployment, int salary) {
        this.employeeID = employeeID;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
    }


    public EmployeeObject(){

    }

    public int getEmployeeID() {
        return employeeID;
    }
    public String getSEmployeeID(){return String.valueOf(employeeID);}

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public void setDateOfBirth(Date dateOfBirth){

        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {

        this.dateOfEmployment = dateOfEmployment;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeObject{" +
                "prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeObject that = (EmployeeObject) o;
        return employeeID == that.employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}
