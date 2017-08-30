package com.example.android.javadevslagos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import java.util.List;
public class DevelopersAdapter extends ArrayAdapter<Developers>  {


    Context context;


    /**
     * An {@link DevelopersAdapter} knows how to create a list item layout for
     * each earthquake in the data source (a list of {@link Developers} objects).
     *
     * These list item layouts will be provided to an adapter view like ListView
     * to be displayed to the user
     *
     * Created by Obidi Isaac on 18/07/2017.
     */

        /**
         * Construct a new {@link DevelopersAdapter}.
         *
         * @param context of the app
         * @param developers is the list of developers, which is the data
         *                    source of the adapter
         */
        public DevelopersAdapter(Context context, List<Developers> developers) {
            super(context, 0, developers);
             this.context = context;
        }

        /**
         * Returns a list view that displays information about the developer at the
         * given position in the list of developers.
         */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if there is an existing list item view (called convertView)
            // that we can reuse, otherwise, if convertView is null, then inflate a
            // new list item layout.

            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.devs_list_items, parent, false);




            }

            // Find the developers at the given position in the list of
            // developers
            Developers currentDevelopers = getItem(position);


            // Find the ImageView with view ID image.
            final ImageView image = (ImageView) listItemView.findViewById(R.id.image);


            // Display the image of the developer in that ImageView.

            Glide.with(context).load(currentDevelopers.getImage()).asBitmap()
                    .centerCrop().placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(image){
                @Override
                        protected void setResource(Bitmap resource){
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    image.setImageDrawable(circularBitmapDrawable);
                }
            }

                    );





            // Find the TextView with view ID username
            TextView username = (TextView) listItemView.findViewById(R.id.username);
            // Display the username of the current developer in that TextView

            username.setText(currentDevelopers.getUsername());

            // Find the TextView with view ID id
            TextView id = (TextView) listItemView.findViewById(R.id.id);
            id.setText("Username");





            // Return the list view that is now showing the appropriate data
            return listItemView;

        }


}