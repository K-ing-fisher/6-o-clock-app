package com.example.leejinyeong.a6oclock.mail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leejinyeong.a6oclock.R;
import com.google.firebase.firestore.FirebaseFirestore;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MailWriteActivity extends AppCompatActivity {

    EditText text;
    Button button;

    private Realm realm;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_write);

        firestore = FirebaseFirestore.getInstance();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();

        //초기화
        realm.deleteRealm(realmConfiguration);

        realm = Realm.getDefaultInstance();

        text = findViewById(R.id.mail);
        button = findViewById(R.id.saveButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        MailDB mail = realm.createObject(MailDB.class);
                        mail.setText(text.getText().toString());
                        mail.setTitle("ASDF");
                    }
                });

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
