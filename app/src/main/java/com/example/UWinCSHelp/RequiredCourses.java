package com.example.UWinCSHelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class RequiredCourses extends AppCompatActivity {
    Button btn_home;
    LinearLayout checklistLayout;

    //llist of required courses
    String[] requiredCourses = {
            "COMP-1000",
            "COMP-1400",
            "COMP-1410",
            "MATH-1020",
            "MATH-1250 / MATH-1260",
            "MATH-1720 / MATH-1760",
            "MATH-1730",
            "COMP-2120",
            "COMP-2140",
            "COMP-2310",
            "COMP-2540",
            "COMP-2560",
            "COMP-2660",
            "STAT-2910 / STAT-2920",
            "COMP-3110",
            "COMP-3150",
            "COMP-3220",
            "COMP-3300",
            "COMP-3540",
            "COMP-3670",
            "MATH-3940 / MATH-3800",
            "COMP-4400",
            "COMP-4540",
            "COMP-4960 / COMP-4990",
            "Language, Art, or Social Science course",
            "Language, Art, or Social Science course",
            "Language, Art, or Social Science course",
            "Additional course: COMP-3XX0 or COMP-4XX0"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_courses);
        checklistLayout = findViewById(R.id.checklistLayout);
        btn_home = findViewById(R.id.button_home);

        // checkboxes get added  dynamically
        for (String course : requiredCourses) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(course);
            checklistLayout.addView(checkBox);
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequiredCourses.this, MainActivity.class);
                startActivity(intent);
                finish(); // closes screen
            }
        });
    }
}