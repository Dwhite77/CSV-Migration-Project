package com.sparta.employee;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EmployeeMain {
    public static void main(String[] args) {
        String line = null;
        String[] fields = null;
        long start;
        long end;

        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        ArrayList<String[]> odditites = new ArrayList<>();
        ArrayList<EmployeeObject> duplicateArrayList = new ArrayList<EmployeeObject>();
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecordsLarge.csv"))) { // were going to auto open and close them with this(autocloseable interface), (try with resources)
            in.readLine();
            while ((line = in.readLine()) != null ) {
                fields = line.split(",");
                boolean dataGate = true;
                try {
                    int employeeID = Integer.parseInt(fields[0]);
                    if (employeeID < 0) dataGate = false;
                    String prefix = fields[1];
                    if (prefix.length() > 5 || !prefix.matches("[a-zA-Z]+\\.")) dataGate = false;
                    String firstName = fields[2];
                    if (firstName.length() >= 50 || !firstName.matches("\\p{L}+")) dataGate = false;
                    String middleInitial = fields[3];
                    if (middleInitial.length() > 3 || !middleInitial.matches("[a-zA-Z]")) dataGate = false;
                    String lastName = fields[4];
                    if (lastName.length() >= 50 || !lastName.matches("\\p{L}+")) dataGate = false;
                    String gender = fields[5];
                    if (!gender.matches("[MmFf]")) dataGate = false;
                    String email = fields[6];
                    if (!email.matches("^(.+)@(.+)\\.(.+)$")) dataGate = false;

                    java.util.Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(fields[7]);
                    java.sql.Date dateOfBirth = new java.sql.Date(dob.getTime());

                    java.util.Date doe = new SimpleDateFormat("MM/dd/yyyy").parse(fields[8]);
                    java.sql.Date dateOfEmployment = new java.sql.Date(doe.getTime());
                    int salary  = Integer.parseInt(fields[9]);
                    if (salary < 0) dataGate = false;
                    if(dataGate){
                    boolean dupeGate = true;
                        EmployeeObject currentEmp = new EmployeeObject(employeeID,prefix,firstName,middleInitial,lastName,gender,email,dateOfBirth,dateOfEmployment,salary);

                        for(int i =0; i< employeeArrayList.size();i++){
                            if(currentEmp.equals(employeeArrayList.get(i))){
                                duplicateArrayList.add(currentEmp);
                                dupeGate = false;
                                break;
                            }
                        }
                        if(dupeGate){
                            employeeArrayList.add(currentEmp);
                        }
                    }
                    else{
                        odditites.add(fields);
                        //System.out.println(fields[0]+" "+fields[1]+" "+fields[2]+" "+fields[3]+" "+fields[4]+" "+fields[5]+" "+fields[6]+" "+fields[7]+" "+fields[8]+" "+fields[9]);
                        // this allows me to check what the errors are
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }
        }

        catch(FileNotFoundException e){ e.printStackTrace();}
        catch (IOException e) { e.printStackTrace(); }

        System.out.println("Valid entires: "+employeeArrayList.size() +"\nDuplicates: "+ duplicateArrayList.size()+"\nOddities: "+odditites.size());

        start = System.currentTimeMillis();
        DatabaseCreation dBCreation = new DatabaseCreation();
        dBCreation.writeToDB(employeeArrayList);
        InsertStatements instance1 = new InsertStatements(employeeArrayList,0, employeeArrayList.size()/2);
        Thread i1 = new Thread(instance1);
        i1.start();
        InsertStatements instance2 = new InsertStatements(employeeArrayList, employeeArrayList.size()/2, employeeArrayList.size());
        Thread i2 = new Thread(instance2);
        i2.start();
        end = System.currentTimeMillis();
        try {
            i1.join();
            i2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //DatabaseCreation.duplicatesToDB(duplicateArrayList);

        System.out.println("Time to insert: "+ (end-start)+"(ms)");
    }
}



