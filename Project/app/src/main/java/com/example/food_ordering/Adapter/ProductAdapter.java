package com.example.food_ordering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_ordering.Models.ImagePosition;
import com.example.food_ordering.Models.ProductRespone;
import com.example.food_ordering.ProductActivity;
import com.example.food_ordering.ProductList;
import com.example.food_ordering.R;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<ProductRespone> productResponeList;
    Context mContext;
    String usn;
    public ProductAdapter(Context Context,List<ProductRespone> productResponeList, String _usn) {
        this.productResponeList = productResponeList;
        this.mContext = Context;
        this.usn = _usn;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproduct,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductRespone product = productResponeList.get(position);
        if(product == null)
        {
            return;
        }

        holder.tv_name.setText("Name: " + product.getProductName());
        holder.tv_price.setText( "Price: " + String.valueOf(product.getPrice()) +"$");
        holder.tv_description.setText( "Description: " + product.getDescription());
        Glide.with(holder.productImage.getContext()).load(productResponeList.get(position).getImagePath()).into(holder.productImage);
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickgotoDetail(product);
            }
        });
    }

    private void onClickgotoDetail(ProductRespone product) {
        Intent intent = new Intent(mContext, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        intent.putExtra("usn2", usn);
        ArrayList<String> imageList = new ArrayList<>();
        for(int i = 0; i < productResponeList.size(); i++) {
            imageList.add(productResponeList.get(i).getImagePath());
        }
        intent.putStringArrayListExtra("listproduct", imageList);


        mContext.startActivity(intent);
    }
    public void release(){
        mContext = null;
    }


    @Override
    public int getItemCount() {
        if(productResponeList != null)
        {
            return productResponeList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price, tv_description;
        ImageView productImage;
        LinearLayout ln;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_description = itemView.findViewById(R.id.tv_description);
            productImage = itemView.findViewById(R.id.productImageurl);
            ln = itemView.findViewById(R.id.itemproduct);
        }
    }
}
