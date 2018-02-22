package com.systems.spanning.platform.match;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.systems.spanning.platform.match.Match;

import java.util.List;

/**
 * Created by Marcus on 2018-02-08.
 */

public class searchMatchAdapter extends RecyclerView.Adapter<searchMatchAdapter.MyViewHolder> {

    private Context mContext;
    private List<Match> matchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView date;
        private TextView location;
        private TextView max_part;
        private TextView min_part;
        private ImageView thumbnail;
        private TextView email;

        private MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            type = view.findViewById(R.id.activityType);
            date = view.findViewById(R.id.activityDate);
            location = view.findViewById(R.id.activityLocation);
            max_part = view.findViewById(R.id.activityMaxPart);
            min_part = view.findViewById(R.id.activityMinPart);
            email = view.findViewById(R.id.activityEmail);

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
        String location = String.format(res.getString(R.string.activity_location), match.getLocation());
        String max = String.format(res.getString(R.string.max_participants), String.valueOf(match.getMax_participants()));
        String min = String.format(res.getString(R.string.min_participants), String.valueOf(match.getMin_participants()));
        String email = String.format(res.getString(R.string.userEmail), match.getEmail());

        if(match.getThumbnail() == 0) {
            holder.type.setText(type);
            holder.date.setText(date);
            holder.location.setText(location);
            holder.max_part.setText(max);
            holder.min_part.setText(min);
            holder.email.setText(email);
        }

        else{
            Glide.with(mContext).load(match.getThumbnail()).into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }
}

