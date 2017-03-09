package scorecard.project.com.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 01/03/2017.
 */

public class QueryUtils {

    public QueryUtils() {
    }

    private static URL createUrl(String stringUrl) {
        Log.i("called", "CreateUrl()");
        URL url = null;
        if (stringUrl == null) {
            return null;
        }
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        Log.i("called", "makehttpRequest()");
        InputStream inputStream = null;
        String jsonResult = "";
        if (url == null) {
            return jsonResult;
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.i("ResponseCode", Integer.toString(urlConnection.getResponseCode()));
            if (urlConnection.getResponseCode() == 200) {
                Log.i("ResponseCode", Integer.toString(urlConnection.getResponseCode()));
                inputStream = urlConnection.getInputStream();
                jsonResult = readFromStream(inputStream);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResult;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        Log.i("called", "readinBackground");
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        Log.i("output", output.toString());
        extractFeatureFromJson(output.toString());
        return output.toString();
    }


    private static List<Books> extractFeatureFromJson(String contentData) {
        Log.i("info", "begin json parsing");
        List<Books> book = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(contentData);

            JSONArray jsonArray = root.getJSONArray("items");
            Log.i("info", String.valueOf(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject volumeInfo = jsonArray.getJSONObject(i);
                String a = volumeInfo.getString("id");
                String author = "";
                JSONObject data = volumeInfo.getJSONObject("volumeInfo");
                String title = data.getString("title");
                String subtitle = data.optString("subtitle");
                String publisher = data.optString("publisher");
                String description = data.optString("description");
                int pageCount = data.optInt("pageCount");

                Log.i("title", title);
                Log.i("subtitle", subtitle);
                Log.i("publisher", publisher);
                Log.i("description", description);
                JSONArray authorArray = data.optJSONArray("authors");
                if (authorArray != null) {
                    for (int j = 0; j < authorArray.length(); j++) {

                        author += authorArray.get(j) + " ";

                    }
                    Log.i("Authors", author);

                }
                book.add(new Books(title, publisher,author, description, pageCount, ""));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Books> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Problem ", e.toString());
        }
        List<Books> books = extractFeatureFromJson(jsonResponse);
        return books;
    }
}

