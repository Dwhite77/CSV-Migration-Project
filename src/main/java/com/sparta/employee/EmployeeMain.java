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
        start = System.currentTimeMillis();
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        ArrayList<String[]> odditites = new ArrayList<>();
        ArrayList<EmployeeObject> duplicateArrayList = new ArrayList<EmployeeObject>();
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecordsLarge.csv"))) { // were going to auto open and close them with this(autocloseable interface), (try with resources)
            in.readLine();

            RegexPatterns regPat = new RegexPatterns();

            while ((line = in.readLine()) != null ) {
                fields = line.split(",");
                boolean dataGate = true;
                try {
                    int employeeID = Integer.parseInt(fields[0]);
                    if (employeeID < 0) dataGate = false;

                    String prefix = fields[1];
                    if (prefix.length() > 5 || !regPat.regexPrefix(prefix)) dataGate = false;

                    String firstName = fields[2];
                    if (firstName.length() > 50 || !regPat.regexName(firstName)) dataGate = false;

                    String middleInitial = fields[3];
                    if (middleInitial.length() > 3 || !regPat.regexMiddleInital(middleInitial)) dataGate = false;

                    String lastName = fields[4];
                    if (lastName.length() > 50 || !regPat.regexName(lastName)) dataGate = false;

                    String gender = fields[5];
                    if (!regPat.regexGender(gender)) dataGate = false;

                    String email = fields[6];
                    if (!regPat.regexEmail(email)) dataGate = false;

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
                            if(currentEmp.equals(employeeArrayList.get(i))){ // maybe try to implement some sort of hashmap here to speed it up, or a stream perhaps?
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
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        catch(FileNotFoundException e){ e.printStackTrace();}
        catch (IOException e) { e.printStackTrace(); }
        end = System.currentTimeMillis();
        System.out.println("Time to create employee objects: "+ (end-start)+"(ms)");
        System.out.println("Valid entires: "+employeeArrayList.size() +"\nDuplicates: "+ duplicateArrayList.size()+"\nCorrupt Data: "+odditites.size());

        start = System.currentTimeMillis();
        DBCreationMYSQL dBCreation = new DBCreationMYSQL();
        dBCreation.writeToDB(employeeArrayList);
        int threads = 48;
        SplitEmpArrList.splitEmpArrThreaded(employeeArrayList,threads);

        end = System.currentTimeMillis();
        //DatabaseCreation.duplicatesToDB(duplicateArrayList);
        int time = Math.toIntExact(end - start);
//        DBCreationMYSQL.createTimeDB();
        DBCreationMYSQL.writeTimeTODB(time,threads);
        System.out.println("Time to insert: "+ (time)+"(ms)");
    }
}
// need to rewatch the 12:15 lecture 7/10/2021


