package com.restaurantapplication.adapter;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurantapplication.R;
import com.restaurantapplication.common.GPSTracker;
import com.restaurantapplication.model.Header;
import com.restaurantapplication.model.RestaurantModel;
import com.squareup.picasso.Picasso;

import java.util.List;



public class RestaurantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RestaurantModel> wallsList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    GPSTracker gps;
    Header header;


    class VHHeader extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public VHHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView)itemView.findViewById(R.id.txtHeader);
        }
    }

    class VHItem extends RecyclerView.ViewHolder{
        ImageView wallpaper;
        TextView dist, name, addr;
        public VHItem(View view) {
            super(view);
            this.wallpaper = (ImageView) view.findViewById(R.id.imageview);
            this.dist = (TextView) view.findViewById(R.id.distance);
            this.name = (TextView) view.findViewById(R.id.name);
            this.addr = (TextView) view.findViewById(R.id.address);

        }
    }

    public RestaurantsAdapter(Context context,Header header, List<RestaurantModel> List) {
        this.wallsList = List;
        this.header = header;
        this.mContext = context;
        gps = new GPSTracker(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return  new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_row, parent, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof VHHeader)
        {
            VHHeader VHheader = (VHHeader)holder;
            VHheader.txtTitle.setText(header.getHeader());
        }
        else if(holder instanceof VHItem)
        {

            RestaurantModel rests = wallsList.get(position-1);
            VHItem VHitem = (VHItem)holder;
            Picasso.with(mContext)
                    .load(rests.getLogo_url()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher)
                    .into(VHitem.wallpaper);
            VHitem.dist.setText(Float.toString(getDistance(gps.getLatitude(), gps.getLongitude(), Double.parseDouble(rests.getLatitude()), Double.parseDouble(rests.getLongitude())))+" Km");
            VHitem.name.setText(rests.getRestaurant_name());
            VHitem.addr.setText(rests.getAddress());

        }


    }
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return wallsList.size()+1;
    }

    public static float getDistance(double startLati, double startLongi, double goalLati, double goalLongi){
        float[] resultArray = new float[99];
        Location.distanceBetween(startLati, startLongi, goalLati, goalLongi, resultArray);
        return Math.round(resultArray[0]/1000);
    }


}
