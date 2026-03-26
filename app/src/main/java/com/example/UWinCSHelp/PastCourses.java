package com.example.UWinCSHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PastCourses extends AppCompatActivity{
    EditText et_course, et_grade;
    Button btn_add, btn_edit, btn_delete, btn_display, btn_home;
    TextView tv_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_courses);

        et_course = findViewById(R.id.editText_course);
        et_grade = findViewById(R.id.editText_grade);

        btn_add = findViewById(R.id.button_add);
        btn_delete = findViewById(R.id.button_delete);
        btn_display = findViewById(R.id.button_display);
        btn_edit = findViewById(R.id.button_edit);

        // ADD THIS LINE HERE
        btn_home = findViewById(R.id.button_home);

        tv_display = findViewById(R.id.textView_display);

        DBCourseHelper courseHelper = new DBCourseHelper(this);
        courseHelper.getReadableDatabase();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course = et_course.getText().toString();
                String grade = et_grade.getText().toString();
                long row = courseHelper.addData(course, grade);
                if(row < 0){
                    Toast.makeText(getApplicationContext(), "Cannot add the data", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Data has been added", Toast.LENGTH_LONG).show();
                }
                et_course.setText("");
                et_grade.setText("");
            }
        });

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor =  courseHelper.displayData();
                if(cursor.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "No Data to Display", Toast.LENGTH_LONG).show();
                }else{
                    String data= "";
                    while (cursor.moveToNext()){
                        data += "course: "+cursor.getString(1)+" grade: "+cursor.getString(2)+"\n";
                    }
                    tv_display.setText(data);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course = et_course.getText().toString();
                Cursor cursor = courseHelper.deleteData(course);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course = et_course.getText().toString();
                String grade = et_grade.getText().toString();
                courseHelper.editData(course, grade);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        PastCourses.this,
                        MainActivity.class
                );

                startActivity(intent);
                finish(); // closes current screen
            }
        });
    }
}
