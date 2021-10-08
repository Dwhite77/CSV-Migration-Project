package com.sparta.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FunctionalProgrammingFileProcessing {

    public static void functionalProgramFileProcessing() {

        try {
            Stream<String> fileStream = Files.lines(Paths.get("EmployeeRecords.csv"));
            fileStream.filter(s->s.split(",")[5].equals("F")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Files.lines(Paths.get(filePath)).map(line -> Arrays.stream(line.split(",")).toArray())
//                .map(line -> tryCleanDates((String[])line))
//                .filter(DataValidator::isValidEmployeeData)
//                .map(this::stringArrToEmployee)
//                .map(WriteableEmployee::new);
//


    }
}
