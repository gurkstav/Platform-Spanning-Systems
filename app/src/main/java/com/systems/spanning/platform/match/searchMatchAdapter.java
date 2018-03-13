package com.systems.spanning.platform.match;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class searchMatchAdapter extends RecyclerView.Adapter<searchMatchAdapter.MyViewHolder> {

    private Context mContext;
    private List<Match> matchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView date;
        private TextView city;
        private ImageView thumbnail;
        private ImageView cardBackground;

        private MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            cardBackground = view.findViewById(R.id.cardBackground);
            type = view.findViewById(R.id.activityType);
            date = view.findViewById(R.id.activityDate);
            city = view.findViewById(R.id.activityCity);
        }
    }


    public searchMatchAdapter(Context mContext, List<Match> matchList) {
        this.mContext = mContext;
        this.matchList = matchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_match, parent, false);

        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Match match = matchList.get(position);
        Resources res = mContext.getResources();
        String type = String.format(res.getString(R.string.activity_type), match.getType());
        String date = String.format(res.getString(R.string.activity_date), match.getDate());
        String location = String.format(res.getString(R.string.activity_city), match.getLocation());
        // String time = String.format(res.getString(R.string.activity_time), match.getTime());


        if(match.getThumbnail() == 1) {
            holder.type.setText(type);
            holder.date.setText(date);
            holder.city.setText(location);
            //holder.time.setText(time);

            switch(match.getType()){
                case "Sport":
                    Glide.with(mContext).load(R.drawable.sport_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                case "Events":
                    Glide.with(mContext).load(R.drawable.event_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                case "Music":
                    Glide.with(mContext).load(R.drawable.music_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                case "Games":
                    Glide.with(mContext).load(R.drawable.games_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                case "Others":
                    Glide.with(mContext).load(R.drawable.other_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.light_grey_background).into(holder.cardBackground);
                    break;
                case "Outdoor":
                    Glide.with(mContext).load(R.drawable.outdoor_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                case "Shopping":
                    Glide.with(mContext).load(R.drawable.shopping_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.card_background).into(holder.cardBackground);
                    break;
                default:
                    Glide.with(mContext).load(R.drawable.other_card).into(holder.thumbnail);
                    Glide.with(mContext).load(R.drawable.light_grey_background).into(holder.cardBackground);
                    break;
            }
        }
        else{
            Glide.with(mContext).load(R.drawable.nomatch).into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public Match getItem(int position) {
        return matchList.get(position);
    }

}


