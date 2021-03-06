package com.example.mikechen.ma4;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import my.DataBase.R;

public class MainActivity extends Activity implements OnClickListener
{

    Button mLogin;
    Button mRegister;

    EditText muname;
    EditText mpassword;

    DBHelper DB = null;

    private static final String TAG="MainActivity";

    /** Called when the activity is first created. */
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()called");
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        Log.d(TAG,"onCreate Called!!!");
        setContentView(R.layout.main);

        DB = new DBHelper(getBaseContext());
        DB.onCreate(DB.getWritableDatabase());

        mRegister = (Button)findViewById(R.id.register);
        mRegister.setOnClickListener(this);

        mLogin = (Button)findViewById(R.id.login);
        mLogin.setOnClickListener(this);

    }
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()called");
    }
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()called");
    }


    public void onClick(View v)
    {
        switch(v.getId())
        {

            case R.id.register:
                Intent i = new Intent(getBaseContext(), Registration.class);
                startActivity(i);
                break;


            case R.id.login:

                muname = (EditText)findViewById(R.id.Ledituname);
                mpassword = (EditText)findViewById(R.id.Leditpw);

                String username = muname.getText().toString();
                String password = mpassword.getText().toString();



                if(username.equals("") || username == null)
                {
                    Toast.makeText(getApplicationContext(), "Please enter User Name", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("") || password == null)
                {
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean validLogin = validateLogin(username, password, getBaseContext());
                    if(validLogin)
                    {
                        System.out.println("In Valid");
                        Intent in = new Intent(getBaseContext(), Home.class);
                        in.putExtra("UserName", muname.getText().toString());
                        startActivity(in);
                        //finish();
                    }
                }
                break;

        }

    }


    private boolean validateLogin(String username, String password, Context baseContext)
    {
        SQLiteDatabase db = DB.getReadableDatabase();

        String[] columns = {"_id"};

        String selection = "username=? AND password=?";
        String[] selectionArgs = {username,password};

        Cursor cursor = null;
        try{

            cursor = db.query(DBHelper.DATABASE_TABLE_REGISTER, columns, selection, selectionArgs, null, null, null);
            startManagingCursor(cursor);
        }
        catch(Exception e)

        {
            e.printStackTrace();
        }
        int numberOfRows = cursor.getCount();

        if(numberOfRows <= 0)
        {

            Toast.makeText(getApplicationContext(), "User Name and Password miss match..\nPlease Try Again", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            return false;
        }

        return true;

    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy()called");
        DB.close();
    }
}
