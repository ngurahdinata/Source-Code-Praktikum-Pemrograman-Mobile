package com.example.sipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class detail_member extends AppCompatActivity {
    String hasilnama, hasilnomber, hasilalamat,hasilgender, hasilpembayaran, hasilinterval;
    TextView tampil_nama,tampil_nomber,tampil_alamat,tampil_gender,tampil_pembayaran,tampil_interval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_member);
        Toast.makeText(detail_member.this, "Berhasil menambahkan member",
                Toast.LENGTH_LONG).show();
        Intent intent=getIntent();
        hasilnama = intent.getStringExtra("KEY_nama");
        hasilnomber = intent.getStringExtra("KEY_nomber");
        hasilalamat = intent.getStringExtra("KEY_alamat");
        hasilgender = intent.getStringExtra("KEY_gender");
        hasilpembayaran = intent.getStringExtra("KEY_pembayaran");
        hasilinterval = intent.getStringExtra("KEY_interval");

        tampil_nama = findViewById(R.id.tv_nama);
        tampil_nomber = findViewById(R.id.tv_telpn);
        tampil_alamat = findViewById(R.id.tv_alamat);
        tampil_gender = findViewById(R.id.tv_gender);
        tampil_pembayaran = findViewById(R.id.tv_pembayaran);
        tampil_interval = findViewById(R.id.tv_lamabayar);

        tampil_nama.setText(hasilnama);
        tampil_nomber.setText(hasilnomber);
        tampil_alamat.setText(hasilalamat);
        tampil_gender.setText(hasilgender);
        tampil_pembayaran.setText(hasilpembayaran);
        tampil_interval.setText(hasilinterval+" Bulan");

    }



    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Dicek lagi ya...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "pause dulu", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        Toast.makeText(getApplicationContext(), "by.. by..", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Udah Sip", Toast.LENGTH_SHORT).show();
    }
    public void bt_home(View view) {
        Intent hmintent = new Intent(detail_member.this, MainActivity.class);
        startActivity(hmintent);
        finish();
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}
