package com.avfsoftware.pelinabeer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avfsoftware.pelinabeer.DetailActivity;
import com.avfsoftware.pelinabeer.Models.Movies;
import com.avfsoftware.pelinabeer.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MovieBaseAdapter extends BaseAdapter {
    private ImageView movieImageThumbnail;
    private TextView movieTitleText;
    private final ArrayList<Movies> movieItems;
    private final Context context;
    private final String imgPath = "https://image.tmdb.org/t/p/original";

    public MovieBaseAdapter(ArrayList<Movies> items, Context context) {
        this.context = context;
        this.movieItems = items;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int position) {
        return movieItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.griditems, parent, false);
        }
        movieImageThumbnail =  convertView.findViewById(R.id.movie_MainImg);
        movieTitleText =  convertView.findViewById(R.id.movie_MainTxt);

        final Movies currentMovie = (Movies) getItem(position);

        Picasso.with(context).load(imgPath + currentMovie.getPoster_path()).into(movieImageThumbnail);

        movieTitleText.setText(currentMovie.getTitle());

        movieImageThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, DetailActivity.class);
                Bundle movieInfo = new Bundle();
                movieInfo.putString("title", currentMovie.getOriginal_title());
                movieInfo.putString("imgPath",  currentMovie.getPoster_path());
                movieInfo.putString("overview", currentMovie.getOverview());
                movieInfo.putString("vote_average", Double.toString(currentMovie.getVote_average()));
                movieInfo.putString("year", currentMovie.getRelease_date().substring(0,4));
                details.putExtras(movieInfo);
                context.startActivity(details);

                Toast.makeText(context, currentMovie.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
