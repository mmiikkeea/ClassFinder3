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
        int i = getCount(scheduleClass.Name);

        SQLiteDatabase db = DB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", scheduleClass.Name);
        values.put("number", scheduleClass.Number);

        if (i != 0) {
            try {
                db.insert(DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES, null, values);

            } catch (Exception e) {
                e.printStackTrace();
            }
            SyncClasses();
        }
    }

    private int getCount(String name) {
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = DB.getReadableDatabase();
            String query = "select count(*) from " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES + " where name = " + name;
            c = db.rawQuery(query, new String[] {"name"});
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void SyncClasses(){
        String sql = "SELECT * FROM " + DBHelper.DATABASE_TABLE_SCHEDULE_CLASSES;
        Cursor cursor = DB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String str = cursor.getString(0);
                int i = cursor.getInt(1);
                Schedule_Class c = new Schedule_Class(str, i, null);
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
