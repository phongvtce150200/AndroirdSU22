package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
     ImageSlider imgSlide;
    RecyclerView.Adapter adapter;
     RecyclerView rcv;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

     ImageView imgv00, imgv01, imgv02, imgv10, imgv11, imgv12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        imgSlide = findViewById(R.id.imageSlide);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
        }
        Toast.makeText(this, getIntent().getStringExtra("usn"), Toast.LENGTH_SHORT).show();

        ArrayList<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel("https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", ScaleTypes.FIT));
        list.add(new SlideModel("https://images.pexels.com/photos/2725744/pexels-photo-2725744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", ScaleTypes.FIT));
        list.add(new SlideModel("https://images.pexels.com/photos/616840/pexels-photo-616840.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", ScaleTypes.FIT));
        list.add(new SlideModel("https://images.pexels.com/photos/1174114/pexels-photo-1174114.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", ScaleTypes.FIT));

        imgSlide.setImageList(list, ScaleTypes.FIT);

        imgv00 = findViewById(R.id.cat00);
        imgv00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ProductList.class).putExtra("catName", "Rice");
                String usn = getIntent().getStringExtra("usn");
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });

        imgv01 = findViewById(R.id.cat01);
        imgv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ProductList.class).putExtra("catName", "Noodle");
                String usn = getIntent().getStringExtra("usn");
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });

        imgv02 = findViewById(R.id.cat02);
        imgv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ProductList.class).putExtra("catName", "Cake");
                String usn = getIntent().getStringExtra("usn");
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });

        imgv10 = findViewById(R.id.cat10);
        imgv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ProductList.class).putExtra("catName", "FastFood");
                String usn = getIntent().getStringExtra("usn");
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });

        imgv11 = findViewById(R.id.cat11);
        imgv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ProductList.class).putExtra("catName", "Drink");
                String usn = getIntent().getStringExtra("usn");
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });

        imgv12 = findViewById(R.id.cat12);
        imgv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, MainActivity.class);
                startActivity(i);
            }
        });


    }


}