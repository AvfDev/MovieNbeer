package com.avfsoftware.pelinabeer.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.avfsoftware.pelinabeer.Adapters.MovieBaseAdapter;
import com.avfsoftware.pelinabeer.Database.DbHandler;
import com.avfsoftware.pelinabeer.Models.Movies;
import com.avfsoftware.pelinabeer.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private GridView grVW;
    private ArrayList<Movies> MovieList;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MovieBaseAdapter mbAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        grVW = view.findViewById(R.id.MoviesGrid3);
         MovieList= new ArrayList<>();

        try{
            SQLiteOpenHelper MoviesHelper = new DbHandler(getActivity().getApplicationContext());
            SQLiteDatabase db = MoviesHelper.getReadableDatabase();
            Cursor cursor = db.query("MYMOVIES",
                    new String[] {"title","poster","original_title", "overview", "release_date","vote_average"},
                    null,null,null,null,null);
           while(cursor.moveToNext()){
                Movies movie = new Movies(
                        cursor.getInt(0),
                        cursor.getDouble(5),
                        cursor.getString(2),
                        10.0,
                        cursor.getString(1).substring(35),
                        cursor.getString(5),
                        cursor.getString(2),
                        "",
                        cursor.getString(3),
                        cursor.getString(4));

                MovieList.add(movie);
             }

        }catch (Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "You don't have any Favorite yet.",Toast.LENGTH_SHORT).show();
        }
        mbAdapter = new MovieBaseAdapter( MovieList,getActivity().getApplicationContext());

        grVW.setAdapter(mbAdapter);
        mbAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

       mbAdapter.notifyDataSetChanged();
    }



    @Override
    public void onStart() {
        super.onStart();
        mbAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
