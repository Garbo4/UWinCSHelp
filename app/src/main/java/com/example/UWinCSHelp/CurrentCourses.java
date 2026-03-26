package com.example.UWinCSHelp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.AlarmManager;
import android.app.PendingIntent;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentCourses extends AppCompatActivity {

    EditText et_course, et_time;
    Button btn_add, btn_display, btn_home;
    TextView tv_display;

    DBCourseHelper courseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_courses);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        et_course = findViewById(R.id.editText_course);
        et_time = findViewById(R.id.editText_time);

        btn_add = findViewById(R.id.button_add);
        btn_display = findViewById(R.id.button_display);
        btn_home = findViewById(R.id.button_home);

        tv_display = findViewById(R.id.textView_display);

        Spinner spinner_day;
        spinner_day = findViewById(R.id.spinner_day);
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        days);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner_day.setAdapter(adapter);

        DBCourseHelper courseHelper = new DBCourseHelper(this); // increase version

        btn_add.setOnClickListener(v -> {
            String course = et_course.getText().toString();
            String day = spinner_day.getSelectedItem().toString();
            String time = et_time.getText().toString();
            long row = courseHelper.addCurrentCourse(course, day, time);
            if(row < 0){
                Toast.makeText(this, "Cannot add course", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Course Added", Toast.LENGTH_LONG).show();
            }
            if(row >= 0){
                scheduleNotification(course, day, time);

                Toast.makeText(this, "Course Added", Toast.LENGTH_LONG).show();
            }
        });

        btn_display.setOnClickListener(v -> {
            Cursor cursor = courseHelper.displayCurrentCourses();
            String data = "";
            while(cursor.moveToNext()){data +=  "Course: " + cursor.getString(1) +  " Day: " + cursor.getString(2) + " Time: " + cursor.getString(3) + "\n";}
            tv_display.setText(data);
        });

        btn_home.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentCourses.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void scheduleNotification(
            String course,
            String day,
            String time){

        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute - 15);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, getDayNumber(day));

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("course", course);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, course.hashCode(), intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }

    private int getDayNumber(String day){

        switch(day){
            case "Tuesday":
                return Calendar.TUESDAY;

            case "Wednesday":
                return Calendar.WEDNESDAY;

            case "Thursday":
                return Calendar.THURSDAY;

            case "Friday":
                return Calendar.FRIDAY;

            default:
                return Calendar.MONDAY;
        }
    }
}