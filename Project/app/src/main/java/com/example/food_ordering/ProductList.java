package com.example.food_ordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.food_ordering.API.ApiClient;
import com.example.food_ordering.Adapter.ProductAdapter;
import com.example.food_ordering.Models.ProductRespone;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.UnsupportedCallbackException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductList extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ProductRespone> mListProduct;
    ImageView img, img2;
    private ProductAdapter productAdapter;
    //String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recycleView);
        img = findViewById(R.id.cat11);
        //String category =  img.getTag().toString();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        mListProduct = new ArrayList<>();
        callApiGetProducts();





    }

    private void callApiGetProducts() {
        try {
            String cat = getIntent().getStringExtra("catName").toString();
            //String usn = getIntent().getStringExtra("usn").toString();
            String usn = getIntent().getStringExtra("usn");

            ApiClient.GetProductService().productResponeCall(cat).enqueue(new Callback<List<ProductRespone>>() {
                @Override
                //call API thanh cong
                public void onResponse(Call<List<ProductRespone>> call, Response<List<ProductRespone>> response) {
                    mListProduct = response.body();
                    ProductAdapter productResponeAdapter = new ProductAdapter(ProductList.this,mListProduct, usn);
                    recyclerView.setAdapter(productResponeAdapter);
                }
                //Call API Fail
                @Override
                public void onFailure(Call<List<ProductRespone>> call, Throwable t) {
                    Toast.makeText(ProductList.this, "Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(ProductList.this, e.toString(), Toast.LENGTH_SHORT).show();
        }



    }

  /*  protected void onDestroy() {
        super.onDestroy();
        if (productAdapter != null) {
            productAdapter.release();
        }
    }*/
}