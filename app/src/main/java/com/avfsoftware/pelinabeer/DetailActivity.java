package com.avfsoftware.pelinabeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avfsoftware.pelinabeer.Database.DbHandler;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxT;
    private TextView overViewTxT;
    private TextView yearTxt;
    private ImageView posterVw;
    private TextView votesTXT;
    private final String imgPath = "https://image.tmdb.org/t/p/original";

    private DbHandler dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        dbHelper = new DbHandler(this);

        titleTxT = findViewById(R.id.titleTxtVw);
        overViewTxT = findViewById(R.id.overviewtxtVw);
        posterVw = findViewById(R.id.posterImgView);
        yearTxt = findViewById(R.id.yearTxtVw);
        votesTXT = findViewById(R.id.VotesTxt);

        Intent intentExtras = getIntent();

        Bundle movieData = intentExtras.getExtras();

        if(!movieData.isEmpty()){
            final String title = movieData.getString("title");
            final String poster = imgPath + movieData.getString("imgPath");
            final String overview = movieData.getString("overview");
            final String year = movieData.getString("year");
            final String votes = movieData.getString("vote_average");

            titleTxT.setText(title);
            overViewTxT.setText(overview);
            yearTxt.setText(year);
            votesTXT.setText(votes);
            Picasso.with(this).load(poster).into(posterVw);
            Log.v("**********POSTER", poster);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        dbHelper.insertMovie(title, poster, title, overview, year, votes);
                        Snackbar.make(view, "Movie added to Favorites", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }catch (Exception e){
                        Snackbar.make(view, "Movie not added to Favorites", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    public void gotoABout(MenuItem item) {
        Intent gotoAboutIt = new Intent(this, AboutActivity.class);
        this.startActivity(gotoAboutIt);
    }

    public void onBackClicked(View view) {
        Intent gotoAboutIt = new Intent(this, MainActivity.class);
        this.startActivity(gotoAboutIt);
    }

    public void onLikened(View view) {
    }
}
