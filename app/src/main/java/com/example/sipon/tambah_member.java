package com.example.sipon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class tambah_member extends AppCompatActivity {

    EditText nama,telepon,alamat;
    Button btn_daftar;
    CheckBox cash, credit, transfer;
    String Getnama, Getphone, Getalamat,Getgender, Getpembayaran,Getlama;
    String Cash, Credit, Transfer, sex="default";
    RadioGroup gender;
    RadioButton rb;
    SeekBar seekBar;
    TextView textView2, TextGender, TextPembayaran;

    private int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_member);

        nama = findViewById(R.id.nama);
        telepon = findViewById(R.id.telepon);
        alamat = findViewById(R.id.alamat);
        btn_daftar = findViewById(R.id.daftar);
        gender = (RadioGroup) findViewById(R.id.gender);
        cash = findViewById(R.id.cash);
        credit = findViewById(R.id.credit);
        seekBar = findViewById(R.id.seekBar);
        textView2 = findViewById(R.id.persentase);
        TextGender = findViewById(R.id.textView8);
        TextPembayaran = findViewById(R.id.textView9);


        textView2.setText(seekBar.getProgress() + "/" + seekBar.getMax() + " Bulan");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText(progress + "/" + seekBar.getMax() + " Bulan");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getnama = nama.getText().toString();
                Getphone = telepon.getText().toString();
                Getalamat = alamat.getText().toString();
                Getgender = sex;
                cek_box();
                Getpembayaran = Cash + Credit + Transfer;
                Getlama = Integer.toString(progress);

                if(nama.getText().toString().length()==0) {
                    nama.setError("Nama diperlukan!");
                    Toast.makeText(tambah_member.this, "Nama Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(telepon.getText().toString().length()<=11){
                    telepon.setError("Telepon diperlukan!");
                    Toast.makeText(tambah_member.this, "No HP Tidak Sesuai Format", Toast.LENGTH_LONG).show();
                } else if(alamat.getText().toString().length()==0) {
                    alamat.setError("Alamat diperlukan!");
                    Toast.makeText(tambah_member.this, "NIK Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(Getgender=="default") {
                    TextGender.setError("Pilihan diperlukan!");
                    Toast.makeText(tambah_member.this, "Gender Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(Getpembayaran.length()==0) {
                    TextGender.setError(null);
                    TextPembayaran.setError("Pilihan diperlukan!");
                    Toast.makeText(tambah_member.this, "Pembayaran Masih Kosong", Toast.LENGTH_LONG).show();
                }else {
                    TextPembayaran.setError(null);
                    shwDialog();
                }



            }
        });

    }
    public void rbclick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pria:
                if (checked)
                    sex = "Pria";
                break;
            case R.id.wanita:
                if (checked)
                    sex = "Wanita";
                break;
        }
    }
    //public void rbclicka (View v) {
    //  int radiobuttonid = gender.getCheckedRadioButtonId();
    //    rb = (RadioButton) findViewById(radiobuttonid);
    //    if (rb.getText().toString().length() == 0) {
    //      textView8.setError("Gender Diperlukan");
    //   }
    // }

    public void cek_box(){
        if(cash.isChecked()){
            Cash ="Cash ";
        }else if(!cash.isChecked()){
            Cash = "";
        }
        if(credit.isChecked()){
            Credit ="Credit ";
        }else if(!credit.isChecked()){
            Credit = "";
        }
    }


    private void shwDialog(){
        Dialog dialog = new Dialog(this) ;
        dialog.setContentView(R.layout.dialogview);

        TextView hasilnama = dialog.findViewById(R.id.tv_nama);
        TextView hasiltelepon = dialog.findViewById(R.id.tv_telpn);
        TextView hasilalamat = dialog.findViewById(R.id.tv_alamat);
        TextView hasilgender = dialog.findViewById(R.id.tv_gender);
        TextView hasilpembayaran = dialog.findViewById(R.id.tv_pembayaran);
        TextView hasillama = dialog.findViewById(R.id.tv_lamabayar);
        Button btkirim = dialog.findViewById(R.id.kirim);

        hasilnama.setText(Getnama);
        hasiltelepon.setText(Getphone);
        hasilalamat.setText(Getalamat);
        hasilgender.setText(Getgender);
        hasilpembayaran.setText(Getpembayaran);
        hasillama.setText(Getlama+" bulan");

        btkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper mydh = new DataHelper(tambah_member.this);
                mydh.addmember(Getnama.trim(), Getphone.trim(),
                        Getalamat.trim(), Getgender.trim(), Getpembayaran.trim(), Integer.valueOf(Getlama));
                Intent intent = new Intent(tambah_member.this,detail_member.class);
                intent.putExtra("KEY_nama", Getnama);
                intent.putExtra("KEY_nomber", Getphone);
                intent.putExtra("KEY_alamat", Getalamat);
                intent.putExtra("KEY_gender", Getgender);
                intent.putExtra("KEY_pembayaran", Getpembayaran);
                intent.putExtra("KEY_interval", Getlama);
                startActivity(intent);
                finish();



            }
        });

        dialog.show();
    }
}