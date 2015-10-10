package com.example.mikechen.ma4;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Welcome1 extends ListActivity{

    private SharedPreferences mPrefs;
    protected TextView eun;
    protected SQLiteDatabase DB;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected TextView mUname;
    protected TextView mFname;
    private DBHelper mHelper;
    private Context mAppContext;
    private String mCurrentUser;
    private static final String PREF_CURRENT_USER="Welcome1.currentUser";
    private static final String PREF_FILE="welcome1";

//    protected TextView mLname;
//    protected TextView mEmail;

/*    private Welcome1(Context appContext){
        mAppContext=appContext;

    }
    above part will cause error
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome1);
/*
        mHelper=new DBHelper(mAppContext);
        mPrefs=mAppContext.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        TextView tv = (TextView) findViewById(R.id.textV);
        String username = tv.getText().toString();
        mCurrentUser=mPrefs.getString(PREF_CURRENT_USER,username);
above part cause error
*/


        DB = (new DBHelper(this)).getWritableDatabase();
        // searchText = (EditText) findViewById (R.id.searchText);

        eun = (TextView) findViewById(R.id.textV);
        Bundle bundle = getIntent().getExtras();

        String UName = bundle.getString("UserName");

        eun.setText(UName);

//        final Button buttonsearch = (Button) findViewById(R.id.buttonSearch);
        final Button buttonsi = (Button) findViewById(R.id.buttonSI);
        buttonsi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Addskin.class);
                startActivity(i);
                //finish();

            }


        });


    }
    public void search(View view) {
        cursor = DB.rawQuery("SELECT _id, firstname, lastname, gender, username FROM registerTB WHERE username LIKE ?",
                new String[]{"%" + eun.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.dtl,
                cursor,
                new String[]{"firstname", "lastname", "gender", "username"},
                new int[]{R.id.sfname, R.id.slname, R.id.sgender, R.id.suname});

        setListAdapter(adapter);
//        
    }
//    show user skill and interest
    /*
    public void search2(View view) {
        cursor = DB.rawQuery("SELECT ,skill,interst FROM skillinterestTB WHERE _id LIKE ?",
                new String[]{"%"+eun.getText().toString()+"%" });
        adapter=new SimpleCursorAdapter(
                this,
                R.layout.dtl,
                cursor,
                new String[]{"skill","interest"},
                new int []{R.id.skillA_spinner,R.id.skillB_spinner}
        );
        */

//    need to show skill, interest from another table
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Welcome1.this, MainActivity.class);
        startActivity(i);
        DB.close();
    }

}
