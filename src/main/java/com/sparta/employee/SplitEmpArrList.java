package com.sparta.employee;

import java.util.ArrayList;

public class SplitEmpArrList {


    public static void splitEmpArrThreaded(ArrayList<EmployeeObject> empArrList, int threads){
        ArrayList<InsertStatements> insStateArrList = new ArrayList<>();
        ArrayList<Thread> threadArrList = new ArrayList<>();
        for(int i =0;i<threads;i++){
            insStateArrList.add(new InsertStatements(empArrList,(i)*(empArrList.size()/threads), (i+1)*(empArrList.size()/threads),i));
            threadArrList.add(new Thread(insStateArrList.get(i)));
        }
        int tArrSize = threadArrList.size();
        for(int i=0; i<tArrSize;i++){
            threadArrList.get(i).start();
        }
         for(int i=0;i<tArrSize;i++){
             try {
                 threadArrList.get(i).join();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}
