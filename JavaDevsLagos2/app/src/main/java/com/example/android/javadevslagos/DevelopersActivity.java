package com.example.android.javadevslagos;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class DevelopersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Developers>>{

    Context context;


    /**URL for java developers data from GITHUB dataset*/
    private static  final String GITHUB_API =
            "https://api.github.com/search/users?q=type:user+location:lagos+language:java";

    public static final String LOG_TAG = DevelopersActivity.class.getName();
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders
     */
    private static final int DEVLOPERS_LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    private DevelopersAdapter mAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: Developer Activity onCreate() called ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);




        // Find a reference to the {@link ListView} in the layout
        final ListView developersListView = (ListView) findViewById(R.id.dev_list);



        developersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)


            @Override
            public void   onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Developers todo = mAdapter.getItem(position);

                String image = todo.getImage();
                String username = todo.getUsername();
                String developerUrl = todo.getDeveloperURL();


                Intent intent = new Intent(getApplicationContext(), DevelopersDetailsActivity.class);
                intent.putExtra("image",image );
                intent.putExtra("username",username );
                intent.putExtra("url", developerUrl);

                View sharedView = developersListView;
                String transitionName = getString(R.string.dev_name);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(DevelopersActivity.this, sharedView, transitionName);
                startActivity(intent, transitionActivityOptions.toBundle());

            }


        });

        // Get the empty state displayed if no developers data to display
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        developersListView.setEmptyView(mEmptyStateTextView);


        // Find a reference to the {@link ListView} in the layout


        mAdapter = new DevelopersAdapter(this, new ArrayList<Developers>());
        developersListView.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);



        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(DEVLOPERS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Developers>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        return  new DevelopersLoader(this, GITHUB_API);

    }

    @Override
    public void onLoadFinished(Loader<List<Developers>> loader, List<Developers> developers) {
        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
//
//        // Set empty state text to display No developer found.
//
         mEmptyStateTextView.setText(R.string.no_developer_found);
//        // Clear the adapter of previous developers data


        mAdapter.clear();

        // If there is a valid list of {@link Developer}s, then add them to
        // the adapter's data set. This will trigger the ListView to update.
        if (developers != null && !developers.isEmpty()) {
            mAdapter.addAll(developers);
        }


    }

    @Override
    public void onLoaderReset(Loader<List<Developers>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");

        // Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }

    /**
     * for the settings activity
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_setting) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}