package com.avfsoftware.pelinabeer.Models;

public class Constants {
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "results";
    private static final String KEY_MOVIE_ID = "id";
    private static final String KEY_MOVIE_TITLE = "title";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_ORIGINAL_LAN = "original_language";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static String getKeySuccess() {
        return KEY_SUCCESS;
    }

    public static String getKeyData() {
        return KEY_DATA;
    }

    public static String getKeyMovieId() {
        return KEY_MOVIE_ID;
    }

    public static String getKeyMovieTitle() {
        return KEY_MOVIE_TITLE;
    }

    public static String getKeyPosterPath() {
        return KEY_POSTER_PATH;
    }

    public static String getKeyOriginalLan() {
        return KEY_ORIGINAL_LAN;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
