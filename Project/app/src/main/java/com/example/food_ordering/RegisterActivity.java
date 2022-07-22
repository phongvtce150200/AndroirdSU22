package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_ordering.API.ApiClient;
import com.example.food_ordering.Models.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, confirmpassword, email,fullname,address,phone;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnSignUp);
        username = findViewById(R.id.edUsername);
        email = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        confirmpassword = findViewById(R.id.edCPassword);
        fullname = findViewById(R.id.edFullName);
        phone = findViewById(R.id.edPhone);
        address = findViewById(R.id.edAddress);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confirmpassword.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(phone.getText().toString()) || TextUtils.isEmpty(fullname.getText().toString()) || TextUtils.isEmpty(address.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this, "All input required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register();
                }
            }
        });

    }

    private void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName(username.getText().toString());
        registerRequest.setPassword(password.getText().toString());
        registerRequest.setConfirmPassword(confirmpassword.getText().toString());
        registerRequest.setFullName(fullname.getText().toString());
        registerRequest.setEmail(email.getText().toString());
        registerRequest.setAddress(address.getText().toString());
        registerRequest.setPhoneNumber(phone.getText().toString());
        Call<RegisterRequest> serverResponeCall = ApiClient.registerService().registerRequestCall(registerRequest);
        serverResponeCall.enqueue(new Callback<RegisterRequest>() {
            @Override
            public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                if(response.isSuccessful())
                {
                    String message = "Successfull";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,Login.class));
                }else {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterRequest> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}