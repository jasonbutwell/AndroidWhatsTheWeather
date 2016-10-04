package com.jasonbutwell.whatstheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String JSONResult = "";

    private String town = "london";
    private String country = "UK";

    // Use your OWN API Key here!
    private String URLtext = "http://api.openweathermap.org/data/2.5/weather?q="+town+","+country+"&APPID=1527e67491f050ac9eefb7c8875e7c6e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadJSONTask JSONTask = new DownloadJSONTask();

        // Async Task to grab the HTML from the web in the background
        JSONTask = new DownloadJSONTask();

        // Exception handling
        try {
            JSONResult = JSONTask.execute(URLtext).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
