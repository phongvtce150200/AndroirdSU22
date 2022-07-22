package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.PhoneAuthProvider;

public class SentOTP extends AppCompatActivity {
    EditText phoneNumber;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_otp);

        phoneNumber = findViewById(R.id.phoneNumber);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString();
                if(phone.isEmpty() == true) {
                    Toast.makeText(SentOTP.this, "Please enter your phone", Toast.LENGTH_SHORT).show();
                } else {
                    if(phone.length() != 10) {
                        Toast.makeText(SentOTP.this, "Phone must be 10 number", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(SentOTP.this, ReciveOTP.class);
                        i.putExtra("phoneNumber", phone);
                        startActivity(i);
                    }

                }

            }
        });


    }
}