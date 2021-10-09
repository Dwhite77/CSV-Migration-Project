package com.sparta.employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class EmployeeMain {

    public static void main(String[] args) {
        String[] fields = null;

        long start;
        long end;
        String filePath = "EmployeeRecordsLarge.csv";
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<EmployeeObject>();
        ArrayList<String[]> oddities = new ArrayList<>();
        ArrayList<EmployeeObject> duplicateArrayList = new ArrayList<EmployeeObject>();
        start = System.currentTimeMillis();
        try {

            ArrayList<String[]> cleanData = (Files.lines(Paths.get(filePath))
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(e -> isValidData(e))
                    .collect(Collectors.toCollection(ArrayList::new)));

            for (String[] line : cleanData) {

                //stringArrToEmployee(line).equals(employeeArrayList.get());
                employeeArrayList.add(stringArrToEmployee(line));


            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

//need to wokr out how to get the duplicated working again
                
//                try {
//                    boolean dupeGate = true;
//                        //EmployeeObject currentEmp = new EmployeeObject(employeeID,prefix,firstName,middleInitial,lastName,gender,email,dateOfBirth,dateOfEmployment,salary);
//
//                        for (EmployeeObject employeeObject : employeeArrayList) {
//                            if (currentEmp.equals(employeeObject)) { // maybe try to implement some sort of hashmap here to speed it up, or a stream perhaps?
//                                duplicateArrayList.add(currentEmp);
//                                dupeGate = false;
//                                break;
//                            }
//                        }
//                        if(dupeGate){
//                            employeeArrayList.add(currentEmp);
//                        }
//
//                    else{
//                        oddities.add(fields);
//                    }
//
//        } catch(IOException e){ e.printStackTrace();}


        end = System.currentTimeMillis();
        System.out.println("Time to create employee objects: "+ (end-start)+"(ms)");
        System.out.println("Valid entires: "+employeeArrayList.size() +"\nDuplicates: "+ duplicateArrayList.size()+"\nCorrupt Data: "+oddities.size());

        start = System.currentTimeMillis();
        DBCreationMYSQL dBCreation = new DBCreationMYSQL();
        dBCreation.writeToDB(employeeArrayList);
        int threads = 60;
        SplitEmpArrList.splitEmpArrThreaded(employeeArrayList,threads);

        end = System.currentTimeMillis();
        //DatabaseCreation.duplicatesToDB(duplicateArrayList);
        int time = Math.toIntExact(end - start);
//        DBCreationMYSQL.createTimeDB();
        DBCreationMYSQL.writeTimeTODB(time,threads);
        System.out.println("Time to insert: "+ (time)+"(ms)");
    }

    


    public static String[] isValidData(String[] fields){
        boolean dataGate = true;
        RegexPatterns regPat = new RegexPatterns();

        if (Integer.parseInt(fields[0]) < 0) dataGate = false;


        if (fields[1].length() > 5 || !regPat.regexPrefix(fields[1])) dataGate = false;

        if (fields[2].length() > 50 || !regPat.regexName(fields[2])) dataGate = false;

        if (fields[3].length() > 3 || !regPat.regexMiddleInital(fields[3])) dataGate = false;

        if (fields[4].length() > 50 || !regPat.regexName(fields[4])) dataGate = false;

        if (!regPat.regexGender(fields[5])) dataGate = false;

        if (!regPat.regexEmail(fields[6])) dataGate = false;

        try {
            java.util.Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(fields[7]);
            Date dateOfBirth = new Date(dob.getTime());
            fields[7] = String.valueOf(dateOfBirth);
        } catch (ParseException e) {
            dataGate = false;
        }

        try {
            java.util.Date doe = new SimpleDateFormat("MM/dd/yyyy").parse(fields[8]);
            java.sql.Date dateOfEmployment = new java.sql.Date(doe.getTime());
            fields[8] = String.valueOf(dateOfEmployment);
        } catch (ParseException e) {
            dataGate = false;
            //e.printStackTrace();
        }

        if (Integer.parseInt(fields[9]) < 0) dataGate = false;
        if(dataGate) return fields;
        else return null;
    }

    public static EmployeeObject stringArrToEmployee(String[] inputString) throws ParseException {
        return new EmployeeObject(Integer.parseInt(inputString[0]),inputString[1],inputString[2],inputString[3],inputString[4],inputString[5],inputString[6],Date.valueOf(inputString[7]),Date.valueOf(inputString[8]),Integer.parseInt(inputString[9]));
    }
}
// need to rewatch the 12:15 lecture 7/10/2021


