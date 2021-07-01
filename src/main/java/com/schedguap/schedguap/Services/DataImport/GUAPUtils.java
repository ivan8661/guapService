package com.schedguap.schedguap.Services.DataImport;

import org.jgrapht.alg.util.Pair;

import java.util.Arrays;

public class GUAPUtils {



    public static Pair<String, String> getStartEndTime(int lessonNum) {
        switch(lessonNum) {
            case 0:
                return new Pair<>("7:50", "9:20");
            case 1:
                return new Pair<>("9:30", "11:00");
            case 2:
                return new Pair<>("11:10", "12:40");
            case 3:
                return new Pair<>("13:00", "14:30");
            case 4:
                return new Pair<>("15:00", "16:30");
            case 5:
                return new Pair<>("16:40", "18:10");
            case 6:
                return new Pair<>("18:30", "20:00");
            case 7:
                return new Pair<>("20:10", "21:40");
            default:
                return new Pair<>("02:28", "13:37");
        }
    }



    public static int[] prepareList(String groups){
        if(groups == null)
            return new int[]{};
        groups = groups.replaceAll("::"," ");
        groups = groups.replaceAll(":", "");
        int[] numArr = Arrays.stream(groups.split(" ")).mapToInt(Integer::parseInt).toArray();
        return numArr;
    }


    public static String getType(String type) {
        switch (type){
            case "ПР":
                return "practice";
            case "Л":
                return "lecture";
            case "ЛР":
                return "lab";
            case "КР":
                return "test";
            case "К":
                return "course";
            default:
                return "undefined";
        }
    }


    public static String getEven(int evenDay) {
        switch(evenDay) {
            case 1:
                return "odd";
            case 2:
                return "even";
            default:
                return "outOfTable";
        }
    }

    public static String getDay(int day){
        switch(day) {
            case 1:
                return "mon";
            case 2:
                return "tue";
            case 3:
                return "wed";
            case 4:
                return "thu";
            case 5:
                return "fri";
            case 6:
                return "sat";
            case 7:
                return "sun";
            default:
                return "NAN";
        }
    }



}
