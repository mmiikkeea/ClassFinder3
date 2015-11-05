package com.example.mikechen.ma4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by groge on 11/3/2015.
 */
public class Schedule_Classes {
    DBHelper DB;
    private ArrayList<Schedule_Class> Classes;

    public Schedule_Classes(Context context){
        DB = DBHelper.getHelper(context);
        Classes = new ArrayList<Schedule_Class>();
        SQLiteDatabase db = DB.getWritableDatabase();
        DB.onCreate(db);
    }

    public void AddClass(Schedule_Class scheduleClass){
        int i = getCount(scheduleClass.CourseNumber, scheduleClass.SectionNumber);

        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("course_number", scheduleClass.CourseNumber);
        values.put("section_number", scheduleClass.SectionNumber);

        if (i != 0) {
            try {
                db.insert(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, null, values);

            } catch (Exception e) {
                e.printStackTrace();
            }
            SyncClasses(scheduleClass.CourseNumber, scheduleClass.SectionNumber);
        }
    }

    private int getCount(double courseNumber, double sectionNumber) {
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = DB.getReadableDatabase();
            String query = "select count(*) from " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES + " where course_number = "
                    + courseNumber + " AND section_number = " + sectionNumber;
            c = db.rawQuery(query, new String[] {"course_number, section_number"});
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void SyncClasses(double section_number, double course_number){
        String sql = "SELECT * FROM " + DBHelper.DATABASE_TABLE_CLASSES + "WHERE section_number = "
                + section_number + "course_number = " + course_number;
        Cursor cursor = DB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(0);
                double course = cursor.getDouble(1);
                double section = cursor.getDouble(2);
                String teacher = cursor.getString(3);
                Schedule_Class c = new Schedule_Class(name, course, section, teacher, null);
                Classes.add(c);
                cursor.moveToNext();
            }
        }
    }

    public void DropClasses(){

    }

    public void ClearClasses(){

    }

    public ArrayList<Schedule_Class> GetClasses(){
        return Classes;
    }
}
