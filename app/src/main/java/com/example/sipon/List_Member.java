package com.example.sipon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Member extends AppCompatActivity {

    RecyclerView recyclerView;

    DataHelper mydh;
    ArrayList <String> id_member, nama_member, hp_member,alamat_member, gender_member, pembayaran_member, interval_member;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_member);

        recyclerView = findViewById(R.id.recyclerView);

        mydh = new DataHelper(List_Member.this);
        id_member = new ArrayList<>();
        nama_member = new ArrayList<>();
        hp_member = new ArrayList<>();


        displayData();

        customAdapter = new CustomAdapter(List_Member.this,this, id_member, nama_member,hp_member);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(List_Member.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = mydh.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                id_member.add(cursor.getString(0));
                nama_member.add(cursor.getString(1));
                hp_member.add(cursor.getString(2));

            }
        }
    }


}