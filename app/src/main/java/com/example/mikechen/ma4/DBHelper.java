package com.example.mikechen.ma4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {


    private SQLiteDatabase db;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_FNAME = "firstname";
    public static final String KEY_LNAME = "lastname";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_USER = "username";
    public static final String KEY_REGISTER_DATE="register_date";

//    course table attribute
    public static final String KEY_CLASS_NUM= "class_num";
    public static final String KEY_SECTION_NUM= "section_num";
    public static final String KEY_PROF="prof_name";
    public static final String KEY_TIMES="times";
    public static final String KEY_ENRLD="enrld_ple";
    public static final String KEY_LIMIT="limit_ple";



    DBHelper DB = null;
    private static final String DATABASE_NAME = "srikanth2.db";
    private static final int DATABASE_VERSION = 2;


    public static final String DATABASE_TABLE_REGISTER = "registerTB";
    private static final String CREATE_TABLE_REGISTER =
            "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_REGISTER + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "firstname TEXT NOT NULL, lastname TEXT NOT NULL, gender TEXT NOT NULL, username TEXT NOT NULL," +
                    " password TEXT NOT NULL);";

    //    another tabel about skill and interest

// HOW TO SET ID ARE THE SAME IN BOTH TABLE?
//    ans: use sql to add the value from XXX to YYY
    //    course table
    public static final String DATABASE_TABLE_COURSE="courseTB";
    private static final String CREATE_TABLE_COURSE =
            "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE_COURSE+"("+KEY_CLASS_NUM+" INTEGER PRIMARY KEY, "+ KEY_SECTION_NUM +
                    " INTEGER PRIMARY KEY, "+ KEY_PROF + " TEXT NOT NULL, "+
                    KEY_TIMES + " TEXT NOT NULL, " +KEY_ENRLD+" INT NOT NULL, "+KEY_LIMIT+" INT NOT NULL);";

    //  Schedule Table
    public static final String DATABASE_TABLE_SCHEDULE_CLASSES="scheduleTB";
    private static final String CREATE_TABLE_SCHEDULE_CLASSES = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE_SCHEDULE_CLASSES+"("+
        "number INT NOT NULL, FOREIGN KEY (course_number) REFERENCES " + DATABASE_TABLE_COURSE + "(" + KEY_CLASS_NUM + ")," +
            " FOREIGN KEY (section_number) REFERENCES " + DATABASE_TABLE_COURSE + "(" + KEY_SECTION_NUM + "));";

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

        this.db = db;

        try{
            db.execSQL(CREATE_TABLE_COURSE);
            db.execSQL(CREATE_TABLE_REGISTER);
            db.execSQL(CREATE_TABLE_SCHEDULE_CLASSES);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String TAG ="onUpgrade";
        Log.w(TAG, "update the database from the version"+oldVersion+"to"+newVersion);

        // TODO Auto-generated method stub

    }

    public Cursor rawQuery(String string, String[] strings) {
        // TODO Auto-generated method stub
        return db.rawQuery(string, strings);
    }

    public void open() {

        getWritableDatabase();
    }






//    public Cursor getDetails(String text) throws SQLException
//    {
//
//        Cursor mCursor =
//                db.query(true, DATABASE_TABLE_REGISTER,
//                        new String[]{KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_GENDER, KEY_USER,KEY_REGISTER_DATE},
//                        KEY_USER + "=" + text,
//                        null, null, null, null,null,null);
//
//        if (mCursor != null)
//        {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//    }
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