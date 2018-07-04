package com.example.leejinyeong.a6oclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leejinyeong.a6oclock.mail.Mail;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Mail> arrayList;

    public RecyclerAdapter(ArrayList<Mail> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View mailView = layoutInflater.inflate(R.layout.mail_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(mailView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mail mail = arrayList.get(position);

        holder.textTitle.setText(mail.getTitle());
        holder.textDate.setText(mail.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}