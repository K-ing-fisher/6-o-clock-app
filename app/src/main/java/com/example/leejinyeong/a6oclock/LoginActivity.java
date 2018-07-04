package com.example.leejinyeong.a6oclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leejinyeong.a6oclock.mail.MailWriteActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 999;

    CallbackManager callbackManager;

    LoginButton loginButton;
    Button accountKitButton;

    AccessToken loginAccessToken;
    com.facebook.accountkit.AccessToken accountKitAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        facebookLogin();
        accountKitLogin();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        loginAccessToken = AccessToken.getCurrentAccessToken();
        boolean loginStatus = loginAccessToken != null && !loginAccessToken.isExpired();
    }

    private void accountKitLogin(){
        accountKitButton = findViewById(R.id.phoneLogin);
        accountKitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AccountKitActivity.class);
                AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                        new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);

                intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void facebookLogin(){
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("result",object.toString());
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();

//                        Intent intent = new Intent(LoginActivity.this, MailWriteActivity.class);
//                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        Log.i("AAA", "실패");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.i("AAA", "실패1");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){

        }
        else{
        }
    }

}
