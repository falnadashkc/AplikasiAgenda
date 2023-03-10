package com.si6a.aplikasiagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TAmbahActivity extends AppCompatActivity {
    private EditText et_tanggal, etjam, etkegiatan;
    private Button btntambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        et_tanggal = findViewById(R.id.et_tanggal);
        etjam = findViewById(R.id.et_jam);
        etkegiatan = findViewById(R.id.et_Kegiatan);
        btntambah = findViewById(R.id.btn_tambah);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal, jam, kegiatan;

                tanggal = et_tanggal.getText().toString();
                jam = etjam.getText().toString();
                kegiatan = etkegiatan.getText().toString();

                if(tanggal.trim().equals("")){
                    et_tanggal.setError("tanggal harus diisi!");
                }
                else if (jam.trim().equals("")){
                    etjam.setError("jam harus diisi");
                }
                else if(kegiatan.trim().equals("")){
                    etkegiatan.setError("kegiatan harus diisi");
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TAmbahActivity.this);
                    long eks = myDB.tambahagenda(tanggal, jam, kegiatan);
                    if(eks == -1){
                        Toast.makeText(TAmbahActivity.this, "Gagal untuk menambah agenda", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(TAmbahActivity.this, "Tambah Agenda sukses", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}