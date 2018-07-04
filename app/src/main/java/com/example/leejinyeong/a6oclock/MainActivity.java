package com.example.leejinyeong.a6oclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.leejinyeong.a6oclock.mail.MailWriteActivity;

public class MainActivity extends AppCompatActivity {

    Button mailButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

        mailButton = findViewById(R.id.mailButton);
        listView = findViewById(R.id.listView);

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MailWriteActivity.class);
                startActivity(intent);
            }
        });


    }
}
