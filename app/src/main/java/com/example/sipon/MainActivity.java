package com.example.sipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.sipon.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    }

    public void bt_member(View view) {
        Intent intent = new Intent(MainActivity.this, tambah_member.class);
        startActivity(intent);
        //  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void bt_member_list(View view) {
        Intent intent = new Intent(MainActivity.this, List_Member.class);
        startActivity(intent);
        //  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}