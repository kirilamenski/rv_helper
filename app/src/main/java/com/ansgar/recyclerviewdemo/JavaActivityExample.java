package com.ansgar.recyclerviewdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class JavaActivityExample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initRecycleView();
    }

    private void initRecycleView() {

    }

    private ArrayList<MainActivity.User> generateList() {
        ArrayList<MainActivity.User> array = new ArrayList();
        for (int i = 0; i < 100; i++) {
            array.add(new MainActivity.User(i, "Name: $it", "SurName: $it"));
        }
        return array;
    }
}
