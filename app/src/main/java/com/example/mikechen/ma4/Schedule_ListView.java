package com.example.mikechen.ma4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by groge on 10/29/2015.
 */
public class Schedule_ListView extends Activity implements View.OnClickListener{

    public ListView mScheduleList;
    Button mAdd;

    ArrayAdapter<String> mAdapter;
    ArrayList<String> mScheduleArray;
    ArrayList<Schedule_Class> mSchedule;

    public static Schedule_Classes sSchedule;

    int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_listview);

        sSchedule = new Schedule_Classes(getBaseContext());

        i = 0;

        mScheduleArray = new ArrayList<String>();
        setScheduleArray();
        mScheduleList = (ListView) findViewById(R.id.schedule_list);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mScheduleArray);
        mScheduleList.setAdapter(mAdapter);

        mAdd = (Button)findViewById(R.id.schedule_add);
        mAdd.setOnClickListener(this);
    }

    private void setScheduleArray() {
        mSchedule = sSchedule.GetClasses();
        Iterator iterator = mSchedule.iterator();
        while (iterator.hasNext()){
            mScheduleArray.add(iterator.toString());
            iterator.next();
        }
    }

    public void addItems(Schedule_Class c) {
        sSchedule.AddClass(c);
        setScheduleArray();
        i++;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.schedule_add:
                Schedule_Class c = new Schedule_Class("name", i, null);
                addItems(c);
                break;
        }
    }
}
