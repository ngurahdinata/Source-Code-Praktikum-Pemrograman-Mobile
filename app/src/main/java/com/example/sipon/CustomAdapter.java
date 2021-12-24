package com.example.sipon;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_member, nama_member, hp_member;
    private Object v;
    Activity activity;


    CustomAdapter(Activity activity, Context context, ArrayList id_member, ArrayList nama_member, ArrayList hp_member){
        this.activity = activity;
        this.context = context;
        this.id_member = id_member;
        this.nama_member = nama_member;
        this.hp_member = hp_member;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_member_txt.setText(String.valueOf(id_member.get(position)));
        holder.nama_member_txt.setText(String.valueOf(nama_member.get(position)));
        holder.hp_member_txt.setText(String.valueOf(hp_member.get(position)));


        holder.mainLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (context, UpdateActivity.class);
                intent.putExtra ("id",String.valueOf(id_member.get(position)));
                intent.putExtra ("nama",String.valueOf(nama_member.get(position)));
                intent.putExtra ("hp",String.valueOf(hp_member.get(position)));

                activity.startActivityForResult (intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_member.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_member_txt, nama_member_txt, hp_member_txt;
        View mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_member_txt = itemView.findViewById(R.id.id_member_txt);
            nama_member_txt = itemView.findViewById(R.id.nama_member_txt);
            hp_member_txt = itemView.findViewById(R.id.hp_member_txt);
            mainLayout = itemView.findViewById (R.id.mainLayout);

        }
    }
}
