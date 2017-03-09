package scorecard.project.com.booklistingapp;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity implements LoaderCallbacks<List<Books>> {
    private BookAdapter mBookAdapter;
    private TextView mEmptyTextView;
    private ImageView mEmptyImageView;
    private int LoaderId = 1;
    private String URLS_TO_FETCH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        String query = getIntent().getStringExtra("bookname");
        Log.i("Value of Query", query);
        ListView listView = (ListView) findViewById(R.id.list_View);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mEmptyImageView = (ImageView) findViewById(R.id.empty_imageView);
        List<Books> books = new ArrayList<Books>();
        URLS_TO_FETCH = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=40";
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        Log.i("Connectivity Info", Boolean.toString(isConnected));
        if (!isConnected) {
            View progress_loader = findViewById(R.id.loading_indicator);
            progress_loader.setVisibility(View.GONE);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.homeRelativeLayout);
            mEmptyImageView.setImageResource(R.drawable.connectivitygone);
            mEmptyTextView.setText(R.string.connectivityError);


        } else {
            mBookAdapter = new BookAdapter(this, books);
            Log.i("info", "flag 5");
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LoaderId, null, this);
            listView.setAdapter(mBookAdapter);
            listView.setEmptyView(mEmptyTextView);
            listView.setEmptyView(mEmptyImageView);
        }
    }

    @Override
    public Loader<List<Books>> onCreateLoader(int id, Bundle args) {

        return new BookLoader(this,URLS_TO_FETCH);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> data) {
        View progress_loader = findViewById(R.id.loading_indicator);
        progress_loader.setVisibility(View.GONE);
        mBookAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mBookAdapter.addAll(data);
        } else {
            mEmptyTextView.setText(R.string.noResultfound);
            mEmptyImageView.setImageResource(R.drawable.sad);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        mBookAdapter.clear();

    }


}



