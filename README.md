# Sort Manager Project

The Sort Manager Project, is a small application used to generate an array of random numbers and them sort them into numerical order.
## Table of Contents
1. [Introduction](#intro)
2. [Instructions](#instructions)
2. [Features](#features)
3. [Project Motivation](#project)
4. [Testing](#testing)
5. [License](#license)

## Introduction <a name="intro"><a/>

## Instructions<a name="instructions"><a/>
1. installation
2. database connection
3. run it
4.


## Features <a name="features"><a/>



![]() (name of picture, then picture url)



## The Motivation behind the project <a name="project"><a/>



#### What problem does it solve?
this project has begun to solve the problem of having to manually move records from a csv file into a more accessible format of a database. not only that but it also does it very quickly with the fastest time of completion being 4 seconds


#### What did I learn?



## Testing <a name="testing"><a/>

During various phases of development there has been a number of JUnit tests that have been carried out, here are couple from various sections of the application.

```java
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
```


## Future Project Direction <a name="future"><a/>
I would like to add in a larger variety of search commands from the running of this file. Currently there is only one cammand that can be utilised which is searching for an employee by ID, I would also like to add the functionality to search for them by name


## License <a name="license"><a/>
[MIT](https://choosealicense.com/licenses/mit/)
