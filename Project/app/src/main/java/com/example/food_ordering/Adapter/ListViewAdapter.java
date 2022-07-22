package com.example.food_ordering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.food_ordering.CartList;
import com.example.food_ordering.Models.CartRespone;
import com.example.food_ordering.ProductList;
import com.example.food_ordering.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<CartRespone> list;
    ArrayList<String> imagelist;
    TextView name, price;
    String ImageUrl;
    ImageView imageView;

    public ListViewAdapter (Context context, ArrayList<CartRespone> arrayList, String ImageUrl, ArrayList<String> _list) {
        this.context = context;
        this.list = arrayList;
        this.ImageUrl = ImageUrl;
        this.imagelist = _list;

    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.customercartview, null);


        name = convertView.findViewById(R.id.pro_name);
        price = convertView.findViewById(R.id.pro_price);
        imageView = convertView.findViewById(R.id.cartImageurl);

        CartRespone cartRespone = list.get(position);

        /*if(cartRespone.get) {
            Glide.with(context).load(ImageUrl).into(imageView);
        }*/


        name.setText(String.valueOf(cartRespone.getProductId()));
        price.setText(String.valueOf(cartRespone.getQuantity()));
        Glide.with(context).load(imagelist.get(position)).into(imageView);
        return convertView;
    }
   /* public void release(){
        context = null;
    }*/
}
