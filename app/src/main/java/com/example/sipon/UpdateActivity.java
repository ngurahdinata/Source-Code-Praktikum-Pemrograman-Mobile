package com.example.sipon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText up_nama, up_hp, up_alamat, up_gender, up_pembayaran, up_lama;
    Button update_btn, delete_btn, hapus;
    String id, nama, hp, alamat, gender, pembayaran, lama;
    String falidnama, falidhp, falidalamat;
    DataHelper mydh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mydh = new DataHelper(UpdateActivity.this);
        up_nama = findViewById(R.id.tv_nama);
        up_hp = findViewById(R.id.tv_telpn);
        up_alamat = findViewById(R.id.tv_alamat);
        up_gender = findViewById(R.id.tv_gender);
        up_pembayaran = findViewById(R.id.tv_pembayaran);
        up_lama = findViewById(R.id.tv_lamabayar);
        update_btn = findViewById(R.id.update_btn);
        ImageButton hapus = findViewById(R.id.hapus);


        getIntendata();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = up_nama.getText().toString();
                hp = up_hp.getText().toString();
                alamat = up_alamat.getText().toString();
                gender= up_gender.getText().toString();
                pembayaran = up_pembayaran.getText().toString();
                lama = up_lama.getText().toString();

                if (nama.compareTo(falidnama)==0 && hp.compareTo(falidhp)==0 && alamat.compareTo(falidalamat)==0 )   {
                    Toast.makeText(UpdateActivity.this, "Tidak ada perubahan data", Toast.LENGTH_LONG).show();
                }else{
                    mydh.updateData(id, nama, hp, alamat, gender, pembayaran, lama);
                }



            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasi();
            }
        });
    }

    void getIntendata() {
        getIntent().hasExtra("nama");
        //get data from intent
        id = getIntent().getStringExtra("id");
        //namaa = getIntent().getStringExtra("nama");
        getData();
    }



    void getData(){
        Cursor cursor = mydh.getdata(id);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                up_nama.setText(cursor.getString(1));
                up_hp.setText(cursor.getString(2));
                up_alamat.setText(cursor.getString(3));
                up_gender.setText(cursor.getString(4));
                up_pembayaran.setText(cursor.getString(5));
                up_lama.setText(cursor.getString(6));

                falidnama = (cursor.getString(1));
                falidhp = (cursor.getString(2));
                falidalamat = (cursor.getString(3));
            }
        }
    }

    void konfirmasi(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data " + falidnama +" ?");
        builder.setMessage("Data akan hilang setelah dihapus apakah yakin ingin menghapus data "+falidnama +" ?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataHelper mydh = new DataHelper(UpdateActivity.this);
                mydh.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
