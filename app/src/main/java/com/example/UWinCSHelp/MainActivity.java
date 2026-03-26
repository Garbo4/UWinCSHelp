package com.example.UWinCSHelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<CardItem> cardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardList = new ArrayList<>();

        cardList.add(new CardItem("Card 1", "Past Courses", R.drawable.box1));
        cardList.add(new CardItem("Card 2", "Current Courses", R.drawable.box2));
        cardList.add(new CardItem("Card 3", "View Required Classes", R.drawable.box3));

        adapter = new RecyclerAdapter(this, cardList);
        recyclerView.setAdapter(adapter);
    }
}