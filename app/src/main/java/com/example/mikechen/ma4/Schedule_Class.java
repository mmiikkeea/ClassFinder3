package com.example.mikechen.ma4;

import java.util.ArrayList;

/**
 * Created by groge on 11/1/2015.
 */
public class Schedule_Class {
    public String Name;
    public double CourseNumber;
    public double SectionNumber;
    public String Teacher;
    public ArrayList<Schedule_Class> Prerequisits;

    public Schedule_Class(String name, double courseNumber, double sectionNumber, String teacher, ArrayList<Schedule_Class> prerequisits){
        Name = name;
        CourseNumber = courseNumber;
        SectionNumber = sectionNumber;
        Teacher = teacher;
        Prerequisits = prerequisits;
    }
}
