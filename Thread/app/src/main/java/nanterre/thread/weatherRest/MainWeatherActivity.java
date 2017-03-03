package nanterre.thread.weatherRest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import nanterre.thread.AsyncResponseJSon;
import nanterre.thread.CommunicatorJSon;
import nanterre.thread.R;

public class MainWeatherActivity extends AppCompatActivity implements AsyncResponseJSon {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);


        CommunicatorJSon c;
        c = new CommunicatorJSon(MainWeatherActivity.this,"","","");
        c.delegate = MainWeatherActivity.this;
        c.execute("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");

    }

    @Override
    public void processFinish(String output) {

        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final String json = output;

        final Weather weather = gson.fromJson(json, Weather.class);

        weather.getQuery().getResults().getChannel().getAtmosphere().getHumidity();

        System.out.println(output);




    }
}
