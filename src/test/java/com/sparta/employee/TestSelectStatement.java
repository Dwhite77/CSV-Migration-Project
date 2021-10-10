package com.sparta.employee;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSelectStatement {

    private SelectStatement sStatement = new SelectStatement();

    @Test
    public void givenAValidEmployeeIDReturnTheirDetails(){ //these are void returns, which makes the difficult to test however they do print to console, so simply by having this small test i can test for errors
        sStatement.getEmployeeByID(2);
    }

    @Test
    public void givenAInvalidEmployeeIDReturnSERR(){
        sStatement.getEmployeeByID(65500);
    }


}
