package com.systems.spanning.platform.match;

import android.content.Context;
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
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
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
        Glide.with(mContext).load(match.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }
}

