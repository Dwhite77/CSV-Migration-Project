package com.sparta.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatterns {

    private Pattern regexPatPrefix = Pattern.compile("^[a-zA-Z]+\\.$");
    private Pattern regexPatName = Pattern.compile("^\\p{L}+$") ;
    private Pattern regexPatMiddleInital = Pattern.compile("^[a-zA-Z]$");
    private Pattern regexPatGender=Pattern.compile("^[MmFf]$");
    private Pattern regexPatEmail =Pattern.compile("^(.+)@(.+)\\.(.+)$");

    public boolean regexPrefix(String strToCompare){
        Matcher matcherPrefix;
        matcherPrefix = regexPatPrefix.matcher(strToCompare);
        return matcherPrefix.find();
    }

    public boolean regexName(String strToCompare){
        Matcher matcherPrefix;
        matcherPrefix = regexPatName.matcher(strToCompare);
        return matcherPrefix.find();
    }

    public boolean regexMiddleInital(String strToCompare){
        Matcher matcherPrefix;
        matcherPrefix = regexPatMiddleInital.matcher(strToCompare);
        return matcherPrefix.find();
    }

    public boolean regexGender(String strToCompare){
        Matcher matcherPrefix;
        matcherPrefix = regexPatGender.matcher(strToCompare);
        return matcherPrefix.find();
    }

    public boolean regexEmail(String strToCompare){
        Matcher matcherPrefix;
        matcherPrefix = regexPatEmail.matcher(strToCompare);
        return matcherPrefix.find();
    }




}
