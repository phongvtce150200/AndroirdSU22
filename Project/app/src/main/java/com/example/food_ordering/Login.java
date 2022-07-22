package com.example.food_ordering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_ordering.API.ApiClient;
import com.example.food_ordering.Models.LoginRequest;
import com.example.food_ordering.Models.LoginRespone;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button loginbtn, loginphone,gglogin;
    EditText username, password;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView tv1 = findViewById(R.id.forgotpassword);
        TextView tv2 = findViewById(R.id.register);
        TextView tv4 = findViewById(R.id.loglinphone);
        //TextView tv5 = findViewById(R.id.GGLogin);
        gglogin = findViewById(R.id.GGLogin);

        username = findViewById(R.id.UserNameTxt);
        password = findViewById(R.id.PasswordTxt);
        loginbtn = findViewById(R.id.login);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        gglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }




        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(Login.this, "Username or Password Cant be null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login();
                }
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SentOTP.class);
                startActivity(i);
            }
        });

     /*   tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                signIn();
            }
        });*/

    }

    void signIn() {
        Intent siginInIntent = gsc.getSignInIntent();
        startActivityForResult(siginInIntent,1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
                //startActivity(new Intent(this,HomePage.class));
                //HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
  /*  private void HomeActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(),HomePage.class);
        startActivity(intent);
    }*/

    private void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(Login.this,HomePage.class);

        startActivity(intent);
    }


    public void login()
    {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<LoginRespone> loginResponeCall = ApiClient.LoginService().loginResponeCall(loginRequest);
        loginResponeCall.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LoginRespone loginRespone = response.body();
                            Intent i = new Intent(Login.this, HomePage.class);
                            i.putExtra("data",loginRespone.getRespone());
                            i.putExtra("usn", username.getText().toString());
                            String sss = username.getText().toString();
                            startActivity(i);

                        }
                    }, 700);
                }
                else
                {


                    Toast.makeText(Login.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}