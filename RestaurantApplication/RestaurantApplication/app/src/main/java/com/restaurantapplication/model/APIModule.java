package com.restaurantapplication.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIModule {

    //http://lit-sea-66228.herokuapp.com:80/tests/c2Utc3Nl
    @GET("tests/c2Utc3Nl")
    Call<ArrayList<RestaurantModel>> getRestaurants();
}
