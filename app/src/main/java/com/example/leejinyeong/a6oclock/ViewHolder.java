package com.example.leejinyeong.a6oclock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textTitle;
    public TextView textDate;

    public ViewHolder(View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.mail_title);
        textDate = itemView.findViewById(R.id.mail_receive_date);
    }
}
