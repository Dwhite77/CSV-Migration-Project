package com.sparta.employee;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSelectStatement {

    private SelectStatement sStatement = new SelectStatement();

    @Test
    public void givenAValidEmployeeIDReturnTheirDetails(){

        sStatement.getEmployeeByID(2);

    }

    @Test
    public void givenAInvalidEmployeeIDReturnSERR(){

        sStatement.getEmployeeByID(64300);
    }

    public void givenAStringReturnSERR(){


    }

}
