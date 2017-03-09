package scorecard.project.com.booklistingapp;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Abhishek on 04/03/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Books>> {

    private String mUrl;

    public BookLoader(Context context, String murl) {

        super(context);
        mUrl = murl;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        QueryUtils abc = new QueryUtils();
        List<Books> result = abc.fetchBookData(mUrl);
        return result;
    }
}

