package com.elllistech.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
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

public class WeatherForecastActivity extends AppCompatActivity
    implements WeatherForecastFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final WeatherForecastFragment
                fragment = new WeatherForecastFragment();
        FragmentManager
                fragmentManager = getSupportFragmentManager();
        FragmentTransaction
                fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.defaultLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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
                        currentCity = citiesSpinner.getSelectedItem().toString(),
                        temperature = "",
                        weatherCondition = "",
                        relativeHumidity = "",
                        windDir = "",
                        windMPH = "",
                        feelslike_f = "",
                        pressureIn = "",
                        precipTodayIn = "",
                        visibilityMI = "",
                        iconURL = "";

                if (!currentCity.equals("Select a city for forecast results"))
                {
                    WeatherAPI
                            weatherData = new WeatherAPI();

                    String
                        data = weatherData.getWeatherForecastData("ID", "/Idaho_Falls.json");
//                Toast.makeText(context, "Hello!",
//                        Toast.LENGTH_LONG).show();

//                    JSONObject
//                            weatherJSONObject = null;
                    try {
                        JSONObject
                            weatherJSONObject = new JSONObject(data),
                            currentObservation = weatherJSONObject.
                                    getJSONObject("current_observation");

                        temperature = currentObservation.getString("temp_f");
                        weatherCondition = currentObservation.getString("weather");
                        relativeHumidity = currentObservation.getString("relative_humidity");
                        windDir = currentObservation.getString("wind_dir");
                        windMPH = currentObservation.getString("wind_mph");
                        feelslike_f = currentObservation.getString("feelslike_f");
                        pressureIn = currentObservation.getString("pressure_in");
                        precipTodayIn = currentObservation.getString("precip_today_in");
                        visibilityMI = currentObservation.getString("visibility_mi");
                        iconURL = currentObservation.getString("icon_url");

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    SharedPreferences.Editor
                            activityDisplay = savedActivities.edit();
                    activityDisplay.putString("currentCity", currentCity);
                    activityDisplay.putString("temperature", temperature);
                    activityDisplay.putString("weatherCondition", weatherCondition);
                    activityDisplay.putString("relativeHumidity", relativeHumidity);
                    activityDisplay.putString("windDir", windDir);
                    activityDisplay.putString("windMPH", windMPH);
                    activityDisplay.putString("feelslike_f", feelslike_f);
                    activityDisplay.putString("pressureIn", pressureIn);
                    activityDisplay.putString("precipTodayIn", precipTodayIn);
                    activityDisplay.putString("visibilityMI", visibilityMI);
                    activityDisplay.putString("iconURL", iconURL);
                    activityDisplay.commit();

                    FragmentManager
                            fragmentManager = getSupportFragmentManager();
                    FragmentTransaction
                            fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.detach(fragment);
                    fragmentTransaction.attach(fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> view) {
                return;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
