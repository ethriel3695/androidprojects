package com.elllistech.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.elllistech.weatherforecast.WeatherAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String
                data = "";
        JSONObject
                weatherJSONObject = null;
        try {
            weatherJSONObject = new JSONObject(data);

            String
                    temperature = weatherJSONObject.getString("temp_f");

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        final Context
                context = this;

        final SharedPreferences
                savedActivities = PreferenceManager.getDefaultSharedPreferences(context);

        final SharedPreferences.Editor
                activityDisplay = savedActivities.edit();

        activityDisplay.putString("currentCity", "Select a city for forecast results");

        activityDisplay.commit();


        ActionBar
                titleBar = getSupportActionBar();

        final Spinner
                citiesSpinner = (Spinner)findViewById(R.id.spinnerCities);

        ArrayAdapter<CharSequence>
                citiesAdapter = ArrayAdapter.createFromResource(this, R.array.cities,
                R.layout.support_simple_spinner_dropdown_item);

        citiesSpinner.setAdapter(citiesAdapter);

//        final ScoresFragment
//                fragment = new ScoresFragment();
//        FragmentManager
//                fragmentManager = getSupportFragmentManager();
//        FragmentTransaction
//                fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.defaultLayout, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                String
                        currentCity = citiesSpinner.getSelectedItem().toString();

                if (!currentCity.equals("Select a city for forecast results"))
                {
                    WeatherAPI
                            weatherData = new WeatherAPI();
                    weatherData.getWeatherForecastData("ID", "/Idaho_Falls.json");
//                Toast.makeText(context, "Hello!",
//                        Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> view) {
                return;
            }
        });
    }

//    public void fragmentInitialization(Spinner east, Spinner west,
//                                       SharedPreferences savedActivities, ScoresFragment fragment) {
//        String
//                eastTeamsCurrentValue = east.getSelectedItem().toString(),
//                westTeamsCurrentValue = west.getSelectedItem().toString();
//
//        SharedPreferences.Editor
//                activityDisplay = savedActivities.edit();
//        activityDisplay.putString("currentEastTeam", eastTeamsCurrentValue);
//        activityDisplay.putString("currentWestTeam", westTeamsCurrentValue);
//        activityDisplay.commit();
//
//        if (!eastTeamsCurrentValue.equals("Default")
//                && !westTeamsCurrentValue.equals("Default"))
//        {
//            TextView
//                    txtDidNotPlay = (TextView)findViewById(R.id.txtDidNotPlay);
//            txtDidNotPlay.setText("");
//
//            FragmentManager
//                    fragmentManager = getSupportFragmentManager();
//            FragmentTransaction
//                    fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.detach(fragment);
//            fragmentTransaction.attach(fragment);
//            fragmentTransaction.commit();
//        }
//        else {
//        }
//    }
}
