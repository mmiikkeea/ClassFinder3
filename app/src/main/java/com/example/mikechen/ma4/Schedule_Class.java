package com.example.mikechen.ma4;

import java.util.ArrayList;

/**
 * Created by groge on 11/1/2015.
 */
public class Schedule_Class {
    public String Name;
    public int Number;
    public ArrayList<Schedule_Class> Prerequisits;

    public Schedule_Class(String name, int number, ArrayList<Schedule_Class> prerequisits){
        Name = name;
        Number = number;
        Prerequisits = prerequisits;
    }
}
