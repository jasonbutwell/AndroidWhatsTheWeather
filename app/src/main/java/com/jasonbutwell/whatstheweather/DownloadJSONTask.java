package com.jasonbutwell.whatstheweather;

/**
 * Created by J on 04/10/2016.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadJSONTask extends AsyncTask<String, Void, String> {

    private URL url;
    private HttpURLConnection urlConnection = null;
    private int data = 0;
    private InputStream in;
    private InputStreamReader reader;
    private StringBuilder contentBuilder;

    @Override
    protected String doInBackground(String... params) {

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection)url.openConnection();

            in = urlConnection.getInputStream();
            reader = new InputStreamReader(in);

            contentBuilder = new StringBuilder();

            while ( (data = in.read() ) != -1)
                contentBuilder.append((char)data);

            String result = contentBuilder.toString();

            return result;

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return null;
    }

    // called when do in background method has completed
    protected void onPostExecute( String result ) {
        super.onPostExecute( result );

        try {
            JSONObject jsonObject = new JSONObject(result);

            // locates the start of the weather array
            String weatherInfo = jsonObject.getString("weather");

            // Parse the array
            JSONArray arr = new JSONArray(weatherInfo);

            for (int i = 0; i < arr.length(); i++ ) {
                JSONObject jsonPart = arr.getJSONObject(i);

                Log.i("main", jsonPart.getString("main"));
                Log.i("description", jsonPart.getString("description"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}