package com.sparta.employee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSelectStatement {
    @Test
    public void givenAValidEmployeeIDReturnTheirDetails(){
        SelectStatement sStatement = new SelectStatement();
        sStatement.getEmployeeByID(1);

    }

//    @Test
//    public void givenAInvalidEmployeeIDReturnTheirDetails(){
//        SelectStatement sStatement = new SelectStatement();
//        sStatement.getEmployeeByID(65001);
//    }

}
