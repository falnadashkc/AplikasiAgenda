package com.si6a.aplikasiagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabtambah;
    private RecyclerView rvagenda;
    private MyDatabaseHelper mydb;
    private AdapterAgenda adAgenda;
    private ArrayList<String> arrTanggal, arrJam, arrKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabtambah = findViewById(R.id.fab_tambah);
        rvagenda = findViewById(R.id.rv_agenda);

        fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TAmbahActivity.class));

            }
        });

        mydb = new MyDatabaseHelper(MainActivity.this);
        arrTanggal = new ArrayList<>();
        arrJam = new ArrayList<>();
        arrKegiatan = new ArrayList<>();
    }
    private void tampilagenda(){
        Cursor varcursor = mydb.bacaDataAgenda();
        if (varcursor.getCount() == 0){
            Toast.makeText(this, "Tidak Ada Data!!", Toast.LENGTH_SHORT).show();
        }
        else {
            arrTanggal.clear();
            arrJam.clear();
            arrKegiatan.clear();
            while (varcursor.moveToNext()){
                arrTanggal.add(varcursor.getString(1));
                arrJam.add(varcursor.getString(2));
                arrKegiatan.add(varcursor.getString(3));
            }
            adAgenda = new AdapterAgenda(MainActivity.this, arrTanggal, arrJam, arrKegiatan);
            rvagenda.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rvagenda.setAdapter(adAgenda);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilagenda();
    }
}