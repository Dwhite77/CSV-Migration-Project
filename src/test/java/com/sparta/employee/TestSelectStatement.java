package com.sparta.employee;


import com.sparta.employee.controller.SelectStatement;
import org.junit.jupiter.api.Test;

public class TestSelectStatement {

    private final SelectStatement sStatement = new SelectStatement();

    @Test
    public void givenAValidEmployeeIDReturnTheirDetails(){ //these are void returns, which makes the difficult to test however they do print to console, so simply by having this small test I can test for errors
        sStatement.getEmployeeByID(2);
    }

    @Test
    public void givenAInvalidEmployeeIDReturnSERR(){
        sStatement.getEmployeeByID(65500);
    }


}
