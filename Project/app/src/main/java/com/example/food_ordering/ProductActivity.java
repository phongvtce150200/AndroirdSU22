package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_ordering.Models.ProductRespone;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    TextView description, title, price;
    ImageView image;
    Button addcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        description = findViewById(R.id.descriptionTxt);
        title = findViewById(R.id.titleTxt);
        price = findViewById(R.id.priceTxt);
        image = findViewById(R.id.imageProd);
        addcart = findViewById(R.id.button4);

        String usn = getIntent().getStringExtra("usn2");

        Toast.makeText(this, usn, Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        ProductRespone product = (ProductRespone) bundle.get("object_product");
        description.setText(product.getDescription());
        title.setText(product.getProductName());
        price.setText(String.valueOf(product.getPrice()) + "$");
        Glide.with(this).load(product.getImagePath()).into(image);

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Bundle bundle1 = getIntent().getExtras();
                ProductRespone product = (ProductRespone) bundle1.get("object_product");*/
                Intent i = new Intent(ProductActivity.this, CartList.class);
                i.putExtra("usn2", usn);
                String sss = getIntent().getStringExtra("usn2");
                int x= product.getProductId();

                ArrayList<String> listimage = getIntent().getStringArrayListExtra("listproduct");

                i.putExtra("proID",String.valueOf(product.getProductId()));
                i.putExtra("name", product.getProductName());
                i.putExtra("price", String.valueOf(product.getPrice()));
                i.putExtra("url", product.getImagePath());
                i.putStringArrayListExtra("listproduct", listimage);
                startActivity(i);
            }
        });

     //   }
    }
}