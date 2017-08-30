package com.example.android.javadevslagos;

import  android.content.AsyncTaskLoader;
import  android.content.Context;
import  android.util.Log;
import  java.util.List;

public class DevelopersLoader extends AsyncTaskLoader<List<Developers>> {


    /**Tag for the Log message*/
    public static final String LOG_TAG =QueryUtils.class.getSimpleName();


    /**Query URL*/

    private String mUrl;


    /**
     * Construct a new {@link Developers}.
     *
     *
     * @param context of the activity
     * @param url to load data from
     */


    public DevelopersLoader(Context context, String url){
        super(context);
        mUrl=url;

    }

    @Override
    protected void onStartLoading(){
        Log.i(LOG_TAG, "TEST: onStartLoading() called....");
        forceLoad();
    }

    /**
     * This is on a background thread
     * @return
     */

    @Override
    public List<Developers> loadInBackground(){
        Log.i(LOG_TAG, "TEST: loadInBackground() called...");


                if(mUrl == null){
                    return null;
                }



        // Perform the network request, parse the response, and extract a list
        // of developers.


        List<Developers> result = QueryUtils.fetchDevelopersData(mUrl);
        return result;
    }















}


