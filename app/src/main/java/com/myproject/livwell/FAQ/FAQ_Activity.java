package com.myproject.livwell.FAQ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.myproject.livwell.MainActivity;
import com.myproject.livwell.R;

import java.util.ArrayList;
import java.util.Objects;

public class FAQ_Activity extends AppCompatActivity {
//    ArrayList<FAQ_DataClass>arrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        // adding data to the arraylist
//        recyclerView = findViewById(R.id.recyclerView);
//        arrayList = new ArrayList<>();
        // arrayList. add(new FAQ_DataClass("What does Bsure.live do?","",false));

    }
}