package com.sparta.employee;

import java.util.ArrayList;

public class SplitEmpArrList {

    public static void splitEmpArrThreaded(ArrayList<EmployeeObject> empArrList, int threads){

        ArrayList<Thread> threadArrList = new ArrayList<>();

        for(int i =0;i<threads;i++){
            if(i!=5) threadArrList.add(new Thread(new InsertStatements(empArrList,(i)*(empArrList.size()/threads), (i+1)*(empArrList.size()/threads),i+1)));
            else threadArrList.add(new Thread(new InsertStatements(empArrList,(i)*(empArrList.size()/threads), empArrList.size(),i+1))); // this is necessary on the last one as there is a rounding error that causes 3 records to be missed out
        }
        int tArrSize = threadArrList.size();
        for(int i=0; i<tArrSize;i++){
            threadArrList.get(i).start();
        }
        for(int i=0;i<tArrSize;i++){
         try {
             threadArrList.get(i).join();
         }
         catch (InterruptedException e) {
             e.printStackTrace();
         }
        }
    }
}
