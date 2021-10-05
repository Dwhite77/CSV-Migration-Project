package com.sparta.employee;

import java.io.*;
import java.util.ArrayList;

public class EmployeeMain {
    public static void main(String[] args) {
        String line = null;
        String[] fields = null;
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecords.csv"))) { // were going to auto open and close them with this(autocloseable interface), (try with resources)
            while ((line = in.readLine()) != null ) {
                fields = line.split(",");
                if(fields.length == 10 &!(fields[0].equals("Emp ID"))){ // if fields is not 10 then we want to move it into the weird input dealings
                    employeeArrayList.add(new EmployeeObject(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],fields[9]));
                    System.out.println("New Employee Object Created");
                }
                else{
                    System.err.println("this is an booky input");
                }
                //this is where we want to create the employee objects and then add them to the collection












            }
        }














        catch(FileNotFoundException e){ e.printStackTrace();}
        catch (IOException e) { e.printStackTrace(); }


    }
}
