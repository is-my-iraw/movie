package com.example.movie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movie.R;
import com.example.movie.model.requets.LoginRequest;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Token;
import com.example.movie.network.ApiManager;
import com.example.movie.network.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    TextView logout;
    ProgressBar signInProgressBar;

    public static void goLogin(Context context) {
        Intent intent = new Intent(context, fragmentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        checkToken();
    }
    private void initView() {
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        Button btLogin = findViewById(R.id.btLogin);
        signInProgressBar=findViewById(R.id.signinprogressbar);
        signInProgressBar.setVisibility(View.GONE);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void onLogin() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            return;
        }
        callApiLogin(username, password);
        signInProgressBar.setVisibility(View.GONE);
        signInProgressBar.setVisibility(View.VISIBLE);
    }

    private void callApiLogin(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        ApiManager.getService().apiLogin(loginRequest).enqueue(new Callback<BaseResponse<Token>>() {
            @Override
            public void onResponse(Call<BaseResponse<Token>> call, Response<BaseResponse<Token>> response) {
                if (response.body() != null) {
                    BaseResponse<Token> res = response.body();
                    String access_token = res.getData().getAccess_token();
                    saveToken(access_token);
                    goHome();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Token>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);
            }
        });
    }

    private void goHome() {
        Intent intent = new Intent(this, fragmentActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkToken() {
        SharedPreferences pref = getSharedPreferences(Constant.ROOT_KET, MODE_PRIVATE);
        String accessToken = pref.getString(Constant.TOKEN, null);
        Constant.ACCESS_TOKEN = accessToken;
        if (accessToken != null) {
            goHome();
        }
    }

    private void saveToken(String access_token) {
        SharedPreferences pref = getSharedPreferences(Constant.ROOT_KET, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constant.TOKEN, access_token);
        editor.commit();
        Constant.ACCESS_TOKEN = access_token;
    }
}