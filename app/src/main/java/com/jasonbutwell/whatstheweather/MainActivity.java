package com.jasonbutwell.whatstheweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String city = "";
    private String country = "UK";

    // Use your OWN API Key here!
    private String APPID = "&APPID=1527e67491f050ac9eefb7c8875e7c6e";

    private String URLText = "http://api.openweathermap.org/data/2.5/weather?q=";

    private String JSONResult = "";
    private String JSONParseURL;
    private DownloadJSONTask JSONTask = null;

    private EditText cityEditText;
    private Button weatherBUtton;
    private TextView weatherTextView;

    private void buildParseURL( String city ) {
        JSONParseURL = ( URLText + city + "," + country + APPID );
        Log.i("PARSE URL", JSONParseURL );
    }

    private void parseJSONWeatherData() {
        // Exception handling

        JSONTask = new DownloadJSONTask(weatherTextView);

        try {
            JSONResult = JSONTask.execute(JSONParseURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void setCityField(View view) {
        String cityTemp = String.valueOf(cityEditText.getText());

        if ( !cityTemp.isEmpty() ) {
            buildParseURL( cityTemp );
            parseJSONWeatherData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Refernces to UI components
        cityEditText    = (EditText) findViewById(R.id.editTextCity);
        weatherBUtton   = (Button) findViewById(R.id.buttonGetWeather);
        weatherTextView = (TextView) findViewById(R.id.weatherTextView);

        JSONParseURL = "";
    }
}
