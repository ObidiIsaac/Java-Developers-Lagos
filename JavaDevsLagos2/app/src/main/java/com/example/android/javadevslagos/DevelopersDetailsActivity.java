package com.example.android.javadevslagos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DevelopersDetailsActivity extends AppCompatActivity {


    String username;
    String developerUrl;


    private boolean zoomOut = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developers_details);


        final Intent intent = getIntent();

        String image;


        final ImageView devImage = (ImageView) findViewById(R.id.img);
        image = String.valueOf(intent.getStringExtra("image"));


        Glide.with(this).load(image)
                .centerCrop().placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(devImage);

        TextView devUsername = (TextView) findViewById(R.id.txt);
        final TextView devUrl = (TextView) findViewById(R.id.devUrl);


        username = String.valueOf(intent.getStringExtra("username"));
        setTitle(username);
        devUsername.setText(username);

        developerUrl = String.valueOf(intent.getStringExtra("url"));
        devUrl.setText(developerUrl);

        Linkify.addLinks(devUrl, Linkify.WEB_URLS);


        devImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (zoomOut) {

                    devImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    devImage.setAdjustViewBounds(true);
                    zoomOut = false;
                } else {
                    devImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    devImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    zoomOut = true;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.share:


                // Create the sharing intent
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Check out this awesome developer. \n  Github Username: " + username + "\n Github URL: " + developerUrl;
                //       sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Subject");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

