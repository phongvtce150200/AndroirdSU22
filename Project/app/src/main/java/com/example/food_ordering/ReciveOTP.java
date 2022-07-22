 package com.example.food_ordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

 public class ReciveOTP extends AppCompatActivity {
     private static final String TAG = "ReciveOTP";
     private FirebaseAuth mAuth;
     String verifycodeSystem;
     Button ok;
     EditText code1, code2, code3, code4, code5, code6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_otp);
        Toast.makeText(this, getIntent().getStringExtra("phoneNumber").toString(), Toast.LENGTH_SHORT).show();

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        mAuth = FirebaseAuth.getInstance();

       // sendCode(phoneNumber);
        ok = findViewById(R.id.ok);
        code1 = findViewById(R.id.opt1);
        code2 = findViewById(R.id.opt2);
        code3 = findViewById(R.id.opt3);
        code4 = findViewById(R.id.opt4);
        code5 = findViewById(R.id.opt5);
        code6 = findViewById(R.id.opt6);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip1 = code1.getText().toString();
                String ip2 = code2.getText().toString();
                String ip3 = code3.getText().toString();
                String ip4 = code4.getText().toString();
                String ip5 = code5.getText().toString();
                String ip6 = code6.getText().toString();
                String codeInput  = ip1 + ip2 + ip3 + ip4 + ip5 + ip6;
                Toast.makeText(ReciveOTP.this, codeInput, Toast.LENGTH_SHORT).show();
            }
        });



    }

     private void sendCode(String phoneNumber) {
         PhoneAuthOptions options =
                 PhoneAuthOptions.newBuilder(mAuth)
                         .setPhoneNumber(phoneNumber)       // Phone number to verify
                         .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                         .setActivity(this)                 // Activity (for callback binding)
                         .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                         .build();
         PhoneAuthProvider.verifyPhoneNumber(options);
     }

     private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
         @Override
         public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
             super.onCodeSent(s, forceResendingToken);

         }

         @Override
         public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
             signinCredential(phoneAuthCredential);
         }

         @Override
         public void onVerificationFailed(@NonNull FirebaseException e) {

         }
     };



     private void signinCredential(PhoneAuthCredential phoneAuthCredential) {

         mAuth.signInWithCredential(phoneAuthCredential)
                 .addOnCompleteListener(ReciveOTP.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             FirebaseUser user = task.getResult().getUser();
                         } else {
                             Toast.makeText(ReciveOTP.this, "onComplete sai roi", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
     }


 }