package com.example.mikechen.ma4;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper


{
    private SQLiteDatabase db;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_FNAME = "firstname";
    public static final String KEY_LNAME = "lastname";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_USER = "username";
    public static final String KEY_REGISTER_DATE="register_date";
//    public static final String KEY_EMAIL = "email";
    public static final String KEY_SKILL="skill";
    public static final String KEY_INTEREST="interest";


    DBHelper DB = null;
    private static final String DATABASE_NAME = "srikanth2.db";
    private static final int DATABASE_VERSION = 2;


    public static final String DATABASE_TABLE_REGISTER = "registerTB";
    private static final String CREATE_TABLE_REGISTER =
            "CREATE TABLE " + DATABASE_TABLE_REGISTER + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "firstname TEXT NOT NULL, lastname TEXT NOT NULL, gender TEXT NOT NULL, username TEXT NOT NULL," +
                    " password TEXT NOT NULL);";

    //    another tabel about skill and interest
    private static final String DATABASE_TABLE_SKILLINTEREST="skillinterestTB";
    private static final String CREATE_TABLE_SKIN=
            "CREATE TABLE"+DATABASE_TABLE_SKILLINTEREST+"("+
                    "_id TEXT NOT NULL,skill TEXT NOT NULL, interest TEXT NOT NULL);";
// HOW TO SET ID ARE THE SAME IN BOTH TABLE?
//    ans: use sql to add the value from XXX to YYY
    private static DBHelper instance;


    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("In constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(CREATE_TABLE_REGISTER);
            db.execSQL(CREATE_TABLE_SKIN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public Cursor rawQuery(String string, String[] strings) {
        // TODO Auto-generated method stub
        return null;
    }

    public void open() {

        getWritableDatabase();
    }

    public Cursor getDetails(String text) throws SQLException
    {

        Cursor mCursor =
                db.query(true, DATABASE_TABLE_REGISTER,
                        new String[]{KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_GENDER, KEY_USER,KEY_REGISTER_DATE},
                        KEY_USER + "=" + text,
                        null, null, null, null,null,null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;



    }
    /*
    public long insertSkillInterest(Addskin addskin){
        ContentValues cv= new ContentValues();
        cv.put(KEY_SKILL,addskin.getSkill1());
        cv.put(KEY_INTEREST,addskin.getInterest());

//        should I input all of the data here?
        return getWritableDatabase().insert(DATABASE_TABLE_SKILLINTEREST,null,cv);

    }
*/

}