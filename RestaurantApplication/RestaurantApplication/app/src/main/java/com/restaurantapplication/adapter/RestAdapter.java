package com.restaurantapplication.adapter;
import com.restaurantapplication.model.APIModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class RestAdapter {

    public static APIModule service;

    static OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
            .addInterceptor(
                    new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder().build();
                            return chain.proceed(request);
                        }
                    }).build();


    public static APIModule getApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://lit-sea-66228.herokuapp.com:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultHttpClient)
                .build();

        return service = retrofit.create(APIModule.class);
    }
}

