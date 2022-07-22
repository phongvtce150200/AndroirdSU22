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

import com.example.food_ordering.Models.CartRespone;
import com.example.food_ordering.PaymentActivity;
import com.example.food_ordering.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<CartRespone> cartResponeList;
    ArrayList<String> imagelist;
    String name, price, imageURL;
    Context context;
    public CartAdapter(String _name, String _price, Context _context, String _imageURL, List<CartRespone> _cartResponeList, ArrayList<String> _list) {
        this.name = _name;
        this.price = _price;
        this.context = _context;
        this.imageURL = _imageURL;
        this.cartResponeList = _cartResponeList;
        this.imagelist = _list;
    }

    //public CartAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customercartview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartRespone cartRespone = cartResponeList.get(position);
        if(cartRespone == null) {return;}

        holder.pro_name.setText(name);
        holder.pro_price.setText(price);

        //Glide.with(context).load(imageURL).into(holder.productImage);
        //holder.quantity.setText(quantity);
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickgotoDetail(cartRespone);
            }
        });
    }

    private void onClickgotoDetail(CartRespone cartRespone) {
        Intent intent = new Intent(context, PaymentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", cartRespone);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public void release(){
        context = null;
    }

    @Override
    public int getItemCount() {
        if(cartResponeList != null) {
            cartResponeList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pro_name, pro_price, quantity;
        LinearLayout ln;
        ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.quantity);
            pro_name = itemView.findViewById(R.id.pro_name);
            pro_price = itemView.findViewById(R.id.pro_price);
            ln = itemView.findViewById(R.id.itemcart);
            productImage = itemView.findViewById(R.id.cartImageurl);

        }
    }

    public String getName() {
        return name;
    }
}
