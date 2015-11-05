package com.example.mikechen.ma4;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mikechen on 10/25/15.
 */
public class Scheduler extends Activity implements OnClickListener{

    private Button addBtn;
    private Button dropBtn;
    private EditText courseNumber;

    protected DBHelper DB = new DBHelper(Scheduler.this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler);

        addBtn = (Button)findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(this);

        dropBtn = (Button)findViewById(R.id.btnDrop);
        dropBtn.setOnClickListener(this);

        
        courseNumber = (EditText)findViewById(R.id.EditCourseNum);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnAdd:
                String course_num=courseNumber.getText().toString();

                boolean invalid = false;

                if(course_num.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter the course number", Toast.LENGTH_SHORT).show();
                }
                else if (invalid==false){
                    addCourse(course_num);

                }
                break;
            case R.id.btnDrop:
        }

    }
    public void addCourse(String coursenum){
        SQLiteDatabase db = DB.getWritableDatabase();
//        SQLiteDatabaseDatabase db= DD.createRegister(values);
        ContentValues values = new ContentValues();
        values.put("course", coursenum);

        // Create a new map of values, where column names are the keys
        String course_num=courseNumber.getText().toString();

        values.put(DBHelper.KEY_CLASS_NUM, course_num);
        values.put(DBHelper.KEY_PROF,"ALEX");
        values.put(DBHelper.KEY_TIMES,"M,W,F");
        values.put(DBHelper.KEY_ENRLD,"30");
        values.put(DBHelper.KEY_LIMIT,"50");

// Insert the new row, returning the primary key value of the new row

        try
        {
            db.insert(DBHelper.DATABASE_TABLE_COURSE, null, values);
            // need to create a new table

            Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    }


