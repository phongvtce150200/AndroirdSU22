package com.example.food_ordering.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_ordering.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    Context context;
    List<Order> orderList;

    public MyOrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //call cac gia tri tu model co bn call bay nhieu nhu o duoi
        holder.txt_num_of_item.setText(new StringBuilder("Num Of Items: ").append(orderList.get(position).getNumOfItem()));

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder() extends RecyclerView.ViewHolder {
        //Truyen tham so cua Order
        @BindView(R.id.txt_order_number)
        TextView txt_order_number;
        //Clone code tren ra co bn tham so truyen bay nhieu
        Unbinder unbinder;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }
}
