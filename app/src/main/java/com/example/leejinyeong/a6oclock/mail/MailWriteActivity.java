package com.example.leejinyeong.a6oclock.mail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leejinyeong.a6oclock.R;
import com.example.leejinyeong.a6oclock.Retrofit.ApiInterface;
import com.example.leejinyeong.a6oclock.Retrofit.ApiUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MailWriteActivity extends AppCompatActivity {

    EditText text;
    Button button;

    private Realm realm;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_write);

        apiInterface = ApiUtils.getApiInterface();

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

                final RealmResults<MailDB> mailDB = realm.where(MailDB.class).findAll();

                sendMail(mailDB);

                finish();
            }
        });
    }

    private void sendMail(RealmResults<MailDB> mailDB){
        apiInterface.postMail("Test", mailDB.last().getText()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
