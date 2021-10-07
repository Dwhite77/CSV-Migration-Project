package com.sparta.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegexPatterns {
    //-------------------------------------------------------------
    // Prefix tests
    //-------------------------------------------------------------
    @Test
    public void givenmrDoesReturnTrue(){
        String testString = "mr.";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexPrefix(testString));
    }
    @Test
    public void givendrDoesReturnTrue(){
        String testString = "dr.";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexPrefix(testString));
    }
    @Test
    public void givenhonsDoesReturnTrue(){
        String testString = "hons.";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexPrefix(testString));
    }
    @Test
    public void givenprofDoesReturnTrue(){
        String testString = "prof.";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexPrefix(testString));
    }
    @Test
    public void givenmrsxDoesReturnTrue(){
        String testString = "mrs.";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexPrefix(testString));
    }
    @Test
    public void givenAnUnacceptablePrefixMissingDotDoesReturnFalse(){
        String testString = "mr";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexPrefix(testString));
    }
    @Test
    public void givenAnUnacceptablePrefixTooLongDoesReturnFalse(){
        String testString = "HisLordShip";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexPrefix(testString));
    }
    @Test
    public void givenAnUnacceptablePrefixWrongCharacterDoesReturnFalse(){
        String testString = "mr@";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexPrefix(testString));
    }
    @Test
    public void givenSQLInjectionPrefixReturnsFalse(){
        String testString = "DROP TABLE EMPLOYEE_DATABASE";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexPrefix(testString));
    }
    //-------------------------------------------------------------
    // Name tests
    //-------------------------------------------------------------
    @Test
    public void givenAFirstNameDoesReturnTrue(){
        String testString = "Dan";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexName(testString));
    }
    @Test
    public void givenALongFirstNameDoesReturnTrue(){
        String testString = "Danielwhiteisactuallymyfirstname";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexName(testString));
    }
    @Test
    public void givenALastNameDoesReturnTrue(){
        String testString = "White";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexName(testString));
    }
    @Test
    public void givenALongLastNameDoesReturnTrue(){
        String testString = "Whiteblackblueyellowbrown";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexName(testString));
    }
    @Test
    public void givenANumberDoesReturnFalse(){
        String testString = "White5";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexName(testString));
    }
    @Test
    public void givenSQLInjectionNameReturnsFalse(){
        String testString = "DROP TABLE EMPLOYEE_DATABASE";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexName(testString));
    }

    //-------------------------------------------------------------
    // Middle Initial tests
    //-------------------------------------------------------------
    @Test
    public void givenAnInitalDoesReturnTrue(){
        String testString = "W";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexMiddleInital(testString));
    }
    @Test
    public void givenAn2InitalsDoesReturnFalse(){
        String testString = "WH";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexMiddleInital(testString));
    }
    @Test
    public void givenAnNumberDoesReturnFalse(){
        String testString = "2";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexMiddleInital(testString));
    }
    @Test
    public void givenSQLInjectionMiddleInitialReturnsFalse(){
        String testString = "DROP TABLE EMPLOYEE_DATABASE";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexMiddleInital(testString));
    }

    //-------------------------------------------------------------
    // Gender tests
    //-------------------------------------------------------------
    @Test
    public void givenMDoesReturnTrue(){
        String testString = "M";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexGender(testString));
    }
    @Test
    public void givenmDoesReturnTrue(){
        String testString = "m";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexGender(testString));
    }
    @Test
    public void givenfDoesReturnTrue(){
        String testString = "f";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexGender(testString));
    }
    @Test
    public void givenFDoesReturnTrue(){
        String testString = "F";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexGender(testString));
    }
    @Test
    public void givenAnGenderNumberDoesReturnFalse(){
        String testString = "2";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexGender(testString));
    }
    @Test
    public void givenAn2GendersDoesReturnFalse(){
        String testString = "Fm";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexGender(testString));
    }
    @Test
    public void givenNotAppropriateValueDoesReturnFalse(){
        String testString = "X";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexGender(testString));
    }
    @Test
    public void givenSQLInjectionGenderReturnsFalse(){
        String testString = "DROP TABLE EMPLOYEE_DATABASE";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexGender(testString));
    }
    //-------------------------------------------------------------
    // Email tests
    //-------------------------------------------------------------
    @Test
    public void givenAnEmailReturnsTrue(){
        String testString = "dan@dan.com";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexEmail(testString));
    }
    @Test
    public void givenAnEmailWithSpecialCharsReturnsTrue(){
        String testString = "dwhite_white99@hotmail.co.uk";
        RegexPatterns regPat = new RegexPatterns();
        assertTrue(regPat.regexEmail(testString));
    }
    @Test
    public void givenAnEmailWithoutATReturnsFalse(){
        String testString = "dwhite_white99hotmail.co.uk";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexEmail(testString));
    }

    @Test
    public void givenSQLInjectionEmailReturnsFalse(){
        String testString = "DROP TABLE EMPLOYEE_DATABASE";
        RegexPatterns regPat = new RegexPatterns();
        assertFalse(regPat.regexEmail(testString));
    }
}
