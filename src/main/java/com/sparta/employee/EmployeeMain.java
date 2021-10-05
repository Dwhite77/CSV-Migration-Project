package com.sparta.employee;

import java.io.*;

public class EmployeeMain {
    public static void main(String[] args) {
        String line = null;
        String[] fields = null;
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecords.csv"))) { // were going to auto open and close them with this(autocloseable interface), (try with resources)
            while ((line = in.readLine()) != null) {
                fields = line.split(",");
                for(String i: fields)
                    System.out.println(i);
                System.out.println("-----------------------------");
                //this is where we want to create the employee objects and then add them to the collection











            }
        }














        catch(FileNotFoundException e){ e.printStackTrace();}
        catch (IOException e) { e.printStackTrace(); }


    }
}
