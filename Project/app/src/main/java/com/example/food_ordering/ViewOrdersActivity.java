package com.example.food_ordering;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.food_ordering.Adapter.MyOrderAdapter;
import com.google.android.gms.common.internal.service.Common;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotDialog;
import io.reactivex.disposables.CompositeDisposable;

public class ViewOrdersActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_order)
    RecyclerView recycler_view_order;

    //Khai Bao API

    //CompositeDisposable compositeDisposable = new CompositeDisposable();
    AlertDialog dialog;

    @Override
    protected void onDestroy(){
        //compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        init();
        initView();

        getAllOrder();
    }
    private void  getAllOrder(){
        dialog.show();
        compositeDisposable.add(API.getOrder(Common.API_KEY,Common.currentUser.getFbid()) //add API call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderModel -> {
                    if(orderModel.isSuccess()){
                        if(orderModel.getResult().size()>0){
                            MyOrderAdapter adapter = new MyOrderAdapter(this,orderModel.getResult());
                            recycler_view_order.setAdapter(adapter);
                        }
                        dialog.dismiss();
                    }
                })); //Call Model costumer



    }
    private void init(){
        dialog = new SpotsDialog.Builder().setCancelable(false).setContext(this).build();
        //Call endpoint cho API
    }
    private void initView(){
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_order.setLayoutManager(layoutManager);
        recycler_view_order.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));

        toolbar.setTitle(getString(R.string.your_order));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}