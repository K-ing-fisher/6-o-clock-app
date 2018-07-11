package com.example.leejinyeong.a6oclock.mail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leejinyeong.a6oclock.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MailWriteActivity extends AppCompatActivity {

    EditText text;
    Button button;
    Button quitButton;

    FirebaseFirestore db;

    Map<String, Object> mail = new HashMap<>();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_write);

        db = FirebaseFirestore.getInstance();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final String time = simpleDateFormat.format(date);

        //초기화
        realm.deleteRealm(realmConfiguration);

        realm = Realm.getDefaultInstance();

        text = findViewById(R.id.mail);
        button = findViewById(R.id.saveButton);
        quitButton = findViewById(R.id.quit_write_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        MailDB mail = realm.createObject(MailDB.class);
                        mail.setText(text.getText().toString());
                        mail.setTitle("ASDF");
                        mail.setDate(time);
                    }
                });

                mail.put("Title", "ASDF");
                mail.put("Text", text.getText().toString());
                mail.put("Date", time);

                db.collection("mail")
                        .add(mail)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            }
                        });

                finish();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
