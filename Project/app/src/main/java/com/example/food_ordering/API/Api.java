package com.example.food_ordering.API;

import com.example.food_ordering.Models.CartRespone;
import com.example.food_ordering.Models.LoginRequest;
import com.example.food_ordering.Models.LoginRespone;
import com.example.food_ordering.Models.ProductRespone;
import com.example.food_ordering.Models.RegisterRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @POST("api/Login")
    Call<LoginRespone> loginResponeCall(@Body LoginRequest loginRequest);

    @GET("api/Products/categoryName"/*"/api/Products"*/)
    Call<List<ProductRespone>> productResponeCall(@Query("categoryName") String categoryName);

    @POST("api/Register")
    Call<RegisterRequest> registerRequestCall(@Body RegisterRequest registerRequest);

    @POST("api/Carts/{ProductId}")
    Call<ArrayList<CartRespone>> cartResponeCall(@Path("ProductId") int ProductId, @Query("usn") String usn );
}
