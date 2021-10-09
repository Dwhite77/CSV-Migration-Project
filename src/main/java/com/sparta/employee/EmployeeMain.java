package com.sparta.employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public class EmployeeMain {

    public static void main(String[] args) {
        String[] fields = null;

        long start;
        long end;
        String filePath = "EmployeeRecords.csv";
        ArrayList<EmployeeObject> employeeArrayList = new ArrayList<>();
        ArrayList<String[]> oddities = new ArrayList<>();
        ArrayList<EmployeeObject> duplicateArrayList = new ArrayList<>();


        start = System.currentTimeMillis();
        try {

            ArrayList<String[]> cleanData = (Files.lines(Paths.get(filePath))
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(EmployeeMain::isValidData) // (e -> isValidData(e))
                    .collect(Collectors.toCollection(ArrayList::new)));
            for (String[] line : cleanData) {
                employeeArrayList.add(stringArrToEmployee(line));
            }

        } catch (IOException | ParseException e) {e.printStackTrace();}

        Set<EmployeeObject> empHashSet = new HashSet<>();
        empHashSet.addAll(employeeArrayList); // very quickly gets rid of duplicates, however i do need to get those duplicates into another array

        duplicateArrayList.addAll(getDuplicates(employeeArrayList));

        employeeArrayList.clear();
        employeeArrayList.addAll(empHashSet);

        end = System.currentTimeMillis();

        System.out.println("Time to create employee objects: "+ (end-start)+"(ms)");
        System.out.println("Valid entires: "+employeeArrayList.size() +"\nDuplicates: "+ duplicateArrayList.size()+"\nCorrupt Data: "+oddities.size());

        start = System.currentTimeMillis();

        DBCreationMYSQL dBCreation = new DBCreationMYSQL();
        dBCreation.writeToDB(employeeArrayList);

        int threads = 6;
        SplitEmpArrList.splitEmpArrThreaded(employeeArrayList,threads);

        end = System.currentTimeMillis();
        //DatabaseCreation.duplicatesToDB(duplicateArrayList);
        int time = Math.toIntExact(end - start);
//        DBCreationMYSQL.createTimeDB();
        DBCreationMYSQL.writeTimeTODB(time,threads);
        System.out.println("Time to insert: "+ (time)+"(ms)");

        while(SelectStatement.databaseReading());
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
        } catch (ParseException e) {dataGate = false;}

        try {
            java.util.Date doe = new SimpleDateFormat("MM/dd/yyyy").parse(fields[8]);
            java.sql.Date dateOfEmployment = new java.sql.Date(doe.getTime());
            fields[8] = String.valueOf(dateOfEmployment);
        } catch (ParseException e) {dataGate = false;}

        if (Integer.parseInt(fields[9]) < 0) dataGate = false;

        if(dataGate) return fields;
        else return null;
    }

    public static EmployeeObject stringArrToEmployee(String[] inputString) throws ParseException {
        return new EmployeeObject(Integer.parseInt(inputString[0]),inputString[1],inputString[2],inputString[3],inputString[4],inputString[5],inputString[6],Date.valueOf(inputString[7]),Date.valueOf(inputString[8]),Integer.parseInt(inputString[9]));
    }

    public static List<EmployeeObject> getDuplicates(final List<EmployeeObject> empList) {
        return getDuplicatesMap(empList).values().stream().filter(duplicates ->duplicates.size()>1).flatMap(Collection::stream).collect(Collectors.toList());

    }
    public static Map<String, List<EmployeeObject>> getDuplicatesMap(List<EmployeeObject> empList){
        return empList.stream().collect(groupingBy(EmployeeObject::getSEmployeeID));
    }






}


