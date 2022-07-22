package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.food_ordering.API.ApiClient;
import com.example.food_ordering.Adapter.CartAdapter;
import com.example.food_ordering.Adapter.ListViewAdapter;
import com.example.food_ordering.Models.CartRespone;
import com.example.food_ordering.Models.ImagePosition;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<CartRespone> mListCart;
    CartAdapter cartAdapter;
    ListViewAdapter listViewAdapter;
    ListView listView;
    ArrayList<ImagePosition> imagePositionArrayList;
    Button backtOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        mListCart = new ArrayList<>();
        imagePositionArrayList = new ArrayList<>();
        listView = findViewById(R.id.blablabla);
        backtOrder = findViewById(R.id.backtoOrder);
        Toast.makeText(this, getIntent().getStringExtra("proID").toString(), Toast.LENGTH_SHORT).show();
        callApiGetCarts();
        backtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, ProductList.class));
            }
        });
    }

    private void callApiGetCarts() {
            String username = getIntent().getStringExtra("usn2");
            String ProductId = getIntent().getStringExtra("proID");
            int prodId = Integer.parseInt(ProductId);
            String ImageURl = getIntent().getStringExtra("url");

           // int id = Integer.parseInt(id1);
            ApiClient.CartService().cartResponeCall(prodId, username).enqueue(new Callback<ArrayList<CartRespone>>() {
                @Override
                public void onResponse(Call<ArrayList<CartRespone>> call, Response<ArrayList<CartRespone>> response) {
                    if(response.isSuccessful())
                    {
                        try {

                            mListCart = response.body();

                            ArrayList<String> listimage = getIntent().getStringArrayListExtra("listproduct");

                            listViewAdapter = new ListViewAdapter(CartList.this, mListCart,ImageURl, listimage);

                            listView.setAdapter(listViewAdapter);
                            listViewAdapter.notifyDataSetChanged();
                            Toast.makeText(CartList.this, "Dung rui", Toast.LENGTH_SHORT).show();
                        }catch (Exception r) {
                            Toast.makeText(CartList.this, r.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(CartList.this, "sai rui", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CartRespone>> call, Throwable t) {
                    Toast.makeText(CartList.this, "sai r", Toast.LENGTH_SHORT).show();
                }
            });


    }

    protected void onDestroy() {
        super.onDestroy();
        if (cartAdapter != null) {
            cartAdapter.release();
        }
    }
}