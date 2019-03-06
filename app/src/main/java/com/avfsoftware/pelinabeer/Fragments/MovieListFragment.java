package com.avfsoftware.pelinabeer.Fragments;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.avfsoftware.pelinabeer.Adapters.MovieBaseAdapter;
import com.avfsoftware.pelinabeer.Models.HJsonParser;
import com.avfsoftware.pelinabeer.Models.Movies;
import com.avfsoftware.pelinabeer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MovieListFragment extends Fragment {

    private ArrayList<HashMap<String, String>> movieList;
    private ArrayList<Movies> MoviesList;
    HashMap<String, String> apiArguments = new HashMap<String, String>();
    private static final String KEY_MOVIE_ID = "id";
    private static final String KEY_MOVIE_TITLE = "title";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_ORIGINAL_LAN = "original_language";
    private static final String KEY_ORIGINAL_TITLE = "original_title";
    private static final String KEY_POPULARITY = "popularity";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String KEY_DATA = "results";
    public Movies moviesIndex;
    private GridView moviesDataGridView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private Calendar calendarDate;
    private SimpleDateFormat dateFormat;

    public MovieListFragment() {
    }

      public static MovieListFragment newInstance(String param1, String param2) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        //Initialize Grid ans Call AsyncTask
        moviesDataGridView = view.findViewById(R.id.MoviesGrid2);
        new FetchMoviesAsyncTask().execute();
        return view;
    }

    private class FetchMoviesAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String todayDate = placeDate();
            String pastDated = placePastdate();
            Log.v("**********TIMEDATE: ", pastDated);
            HJsonParser httpJsonParser = new HJsonParser();
            apiArguments.put("api_key", "637ec0d57ac808d5fc04045119ff1851");
            apiArguments.put("sort_by", "vote_average.desc");
            apiArguments.put("primary_release_date.gte", pastDated);
            apiArguments.put("primary_release_date.lte", todayDate);
            apiArguments.put("certification_country", "USA");
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "discover/movie", "GET", apiArguments);
            try {
                int success = jsonObject.getInt("page");
                JSONArray moviesJsonArray;
                if (success == 1) {
                    movieList = new ArrayList<>();
                    MoviesList = new ArrayList<>();
                    moviesJsonArray = jsonObject.getJSONArray(KEY_DATA);
                    //Iterate through the response and populate moviesJsonArray list
                    for (int i = 0; i < moviesJsonArray.length(); i++) {
                        JSONObject movieFromApi = moviesJsonArray.getJSONObject(i);
                        moviesIndex = new Movies(movieFromApi.getInt(KEY_MOVIE_ID), movieFromApi.getDouble(KEY_VOTE_AVERAGE),
                                movieFromApi.getString(KEY_MOVIE_TITLE), movieFromApi.getDouble(KEY_POPULARITY),
                                movieFromApi.getString(KEY_POSTER_PATH), movieFromApi.getString(KEY_ORIGINAL_LAN),
                                movieFromApi.getString(KEY_ORIGINAL_TITLE), movieFromApi.getString("genre_ids"),
                                movieFromApi.getString("overview"), movieFromApi.getString("release_date"));

                        MoviesList.add(moviesIndex);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    protected void onPostExecute(String result) {
            MovieBaseAdapter mbAdapter = new MovieBaseAdapter(MoviesList,getActivity().getApplicationContext());
            moviesDataGridView.setAdapter(mbAdapter);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public String placeDate(){
        calendarDate = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(calendarDate.getTime());
        return todayDate;
    }

    public String placePastdate (){
        calendarDate.add(Calendar.DATE, -15);
        String pastDate = dateFormat.format(calendarDate.getTime());
        return pastDate;
    }
}
