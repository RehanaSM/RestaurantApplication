package com.restaurantapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurantapplication.R;
import com.restaurantapplication.adapter.RestAdapter;
import com.restaurantapplication.adapter.RestaurantsAdapter;
import com.restaurantapplication.common.DialogUtility;
import com.restaurantapplication.google_map.GoogleMapActivity;
import com.restaurantapplication.listeners.ClickListener;
import com.restaurantapplication.listeners.DividerItemDecoration;
import com.restaurantapplication.listeners.RecyclerTouchListener;
import com.restaurantapplication.model.APIModule;
import com.restaurantapplication.model.Header;
import com.restaurantapplication.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreakFragment extends Fragment {

    private RecyclerView recyclerView;
    private RestaurantsAdapter mAdapter;
    private TextView loc;
    Header mHeader;
    private List<RestaurantModel> list = new ArrayList<>();

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        list.clear();
        mHeader = new Header();

        GetRestaurantsData();
        // loc = (TextView) view.findViewById(R.id.current_ation);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new RestaurantsAdapter(getActivity(), mHeader, list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), GoogleMapActivity.class);

                intent.putExtra("latitude", list.get(position).getLatitude());
                intent.putExtra("longitude", list.get(position).getLongitude());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }


    private void GetRestaurantsData() {
        DialogUtility.showProgressDialog(getActivity(), false, "Data fetching from server");
        APIModule api = RestAdapter.getApiService();
        Call<ArrayList<RestaurantModel>> call = api.getRestaurants();
        call.enqueue(new Callback<ArrayList<RestaurantModel>>() {
            @Override
            public void onResponse(Response<ArrayList<RestaurantModel>> response) {
                DialogUtility.pauseProgressDialog();
                list.addAll(response.body());
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Throwable t) {
                DialogUtility.pauseProgressDialog();
                Toast.makeText(getActivity(), " " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
