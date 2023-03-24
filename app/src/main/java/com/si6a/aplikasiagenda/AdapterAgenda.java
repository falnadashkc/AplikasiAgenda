package com.si6a.aplikasiagenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.VHAgenda> {
    private Context ctx;
    private ArrayList arrTanggal, arrJam, arrKegiatan, arrid;

    public AdapterAgenda(Context ctx, ArrayList arrid, ArrayList arrTanggal, ArrayList arrJam, ArrayList arrKegiatan) {
        this.ctx = ctx;
        this.arrTanggal = arrTanggal;
        this.arrJam = arrJam;
        this.arrKegiatan = arrKegiatan;
        this.arrid = arrid;
    }

    @NonNull
    @Override
    public VHAgenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_agenda, parent, false);
        return new VHAgenda(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAgenda holder, int position) {
        holder.tvid.setText(arrid.get(position).toString());
        holder.tvTanggal.setText(arrTanggal.get(position).toString());
        holder.tvJam.setText(arrJam.get(position).toString());
        holder.tvKegiatan.setText(arrKegiatan.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrTanggal.size();
    }

    public class VHAgenda extends RecyclerView.ViewHolder{
        TextView tvTanggal, tvJam, tvKegiatan, tvid;
        public VHAgenda(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tp_id);
            tvTanggal = itemView.findViewById(R.id.tp_tanggal);
            tvJam = itemView.findViewById(R.id.tp_jam);
            tvKegiatan = itemView.findViewById(R.id.tp_kegiatan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Anjay");
                    pesan.setMessage("OM, MAU NGAPAIN OM?" + tvid.getText().toString() + ". JANGAN OM!");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper mydb = new MyDatabaseHelper(ctx);
                            long eks = mydb.hapusagenda(tvid.getText().toString());
                            if(eks == -1){
                                Toast.makeText(ctx, "nggk bisa cuk", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "ANJAY GG GEMING!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    pesan.setPositiveButton("ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    pesan.show();
                    return false;

                }
            });
        }
    }
}
