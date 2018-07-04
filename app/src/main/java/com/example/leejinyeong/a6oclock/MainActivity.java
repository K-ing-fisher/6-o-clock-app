package com.example.leejinyeong.a6oclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leejinyeong.a6oclock.mail.MailWriteActivity;
import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {

    Button mailButton;
    Button mailStorageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(AccessToken.getCurrentAccessToken() == null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        mailButton = findViewById(R.id.mailButton);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MailWriteActivity.class);
                startActivity(intent);
            }
        });

        mailStorageButton = findViewById(R.id.mailStorageButton);
        mailStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MailStorageActivity.class);
                startActivity(intent);
            }
        });
    }
}
