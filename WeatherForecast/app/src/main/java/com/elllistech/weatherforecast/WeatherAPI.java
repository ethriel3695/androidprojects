package com.elllistech.weatherforecast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ethri on 10/10/2016.
 */

public class WeatherAPI {
    private static String
        _baseURL = "http://api.wunderground.com/api/bdc9d3af9b259532/conditions/q/";

    public String getWeatherForecastData(String state, String city) {
        HttpURLConnection conn = null;
        InputStream weatherStream = null;

        try {
            conn = (HttpURLConnection)(new URL(_baseURL + state + city)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            StringBuffer
                    weatherBuffer = new StringBuffer();
            weatherStream = conn.getInputStream();
            BufferedReader
                    weatherReader = new BufferedReader(new InputStreamReader(weatherStream));
            String
                    weatherDataLine = null;
            while ((weatherDataLine = weatherReader.readLine()) != null) {
                weatherBuffer.append(weatherDataLine);
            }

            weatherStream.close();
            conn.disconnect();
            //Log.d(weatherBuffer.toString(), "getWeatherForecastData: ");

            return weatherBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try { weatherStream.close(); } catch(Throwable t) {}
            try { conn.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }

    public Bitmap getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            URL urlConnection = new URL(code);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
