package com.example.leejinyeong.a6oclock;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.leejinyeong.a6oclock.mail.Mail;
import com.example.leejinyeong.a6oclock.mail.MailDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MailStorageActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView temp;

    ArrayList<Mail> mailArrayList;

    RecyclerAdapter recyclerAdapter;

    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_storage);

        mailArrayList = new ArrayList<>();

        quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        db = FirebaseFirestore.getInstance();

        setMailArrayList();

        recyclerAdapter = new RecyclerAdapter(mailArrayList);
        RecyclerView recyclerView = findViewById(R.id.recive_mail_list);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MailStorageActivity.this));
    }

    void setMailArrayList(){

        db.collection("mail")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Mail mail = new Mail();
                            mail.setTitle(document.getData().get("Title").toString());
                            mail.setDate(document.getData().get("Date").toString());
                            mail.setText(document.getData().get("Text").toString());

                            mailArrayList.add(mail);

                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
