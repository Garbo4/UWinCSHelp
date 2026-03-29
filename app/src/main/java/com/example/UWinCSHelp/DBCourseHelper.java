package com.example.UWinCSHelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCourseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "test_database";
    private static final int DB_VERSION = 15; // change for no db overlap

    public DBCourseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE course(_id INTEGER PRIMARY KEY AUTOINCREMENT, course TEXT, grade TEXT)";
        sqLiteDatabase.execSQL(query);
        String query2 = "CREATE TABLE current_course(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "course TEXT," + "day TEXT," +"time TEXT)";
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS course");
        db.execSQL("DROP TABLE IF EXISTS current_course");
        onCreate(db);
    }
    public long addData(String courseInput, String gradeInput){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course", courseInput);
        contentValues.put("grade", gradeInput);
        return sqLiteDatabase.insert("course", null,contentValues);
    }
    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course", null);
        return cursor;
    }

    public Cursor deleteData(String inputcourse){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE course=?", new String[]{inputcourse});
        if(cursor.getCount()>0){
            sqLiteDatabase.delete("course", "course = ?", new String[]{inputcourse});
        }
        return cursor;
    }

    public long editData(String inputcourse, String inputgrade){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", inputgrade);

        return sqLiteDatabase.update("course", contentValues, "course=?", new String[]{inputcourse} );
    }

    public long addCurrentCourse(String courseInput, String dayInput, String timeInput){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course", courseInput);
        values.put("day", dayInput);
        values.put("time", timeInput);
        return db.insert("current_course", null, values);
    }

    public Cursor displayCurrentCourses(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM current_course", null);
    }
}
