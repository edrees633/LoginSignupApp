package com.example.loginsignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class  PartDetailsActivity extends AppCompatActivity {

    private TextView tvName, tvDescription,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_details);

        Intent i=this.getIntent();
        Part part = (Part) i.getSerializableExtra("part");

    }
}