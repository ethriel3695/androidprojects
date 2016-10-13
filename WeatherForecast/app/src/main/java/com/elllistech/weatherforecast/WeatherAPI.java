package com.elllistech.weatherforecast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author: Reuben Ellis
 * Date: 10/12/2016
 * The WeatherAPI class provides weather data for the application
 * and the weather data API is from www.wunderground.com
 * The base URL is declared at the top of the class because for the current
 * use of the application, it will not change
 */

public class WeatherAPI {
    private static String
        _baseURL = "http://api.wunderground.com/api/bdc9d3af9b259532/conditions/q/";

    /**
     * The getWeatherForecastData method creates an HTTP Connection and
     * Input stream to get the results from the weather API
     * The HttpUrlConnection accepts the full API Url and opens the connection
     * The request method is set to GET and input and output is also set to true
     * A StringBuffer and BufferReader are used to to get the results of the Input Stream
     * A string variable accepts the results of each line of the BufferReader until the
     * BufferReader is null and then the while loop is exited
     * The InputStream is closed and the HTTP connection is disconnected and then the method
     * returns the string variable for data consumption
     * If an exception is thrown, the catch clause of the try-catch-finally will
     * display a message indicating the error and then the finally block closes all resources
     * and the method returns a null value rather than a data string
     * @param state is the value of the state based on the city choice from the dropdown Spinner
     *              The state variable will always be a two letter abbreviation of the state
     * @param city is the value of the city chosen from the dropdown and formatted in the
     *             following way "/cityName.json"
     * @return
     */
    public String getWeatherForecastData(String state, String city) {
        HttpURLConnection
                weatherConnection = null;
        InputStream
                weatherStream = null;

        try {
            weatherConnection =
                    (HttpURLConnection)(new URL(_baseURL + state + city)).openConnection();
            weatherConnection.setRequestMethod("GET");
            weatherConnection.setDoInput(true);
            weatherConnection.setDoOutput(true);
            weatherConnection.connect();

            StringBuffer
                    weatherBuffer = new StringBuffer();
            weatherStream = weatherConnection.getInputStream();
            BufferedReader
                    weatherReader = new BufferedReader(new InputStreamReader(weatherStream));
            String
                    weatherDataLine = null;
            while ((weatherDataLine = weatherReader.readLine()) != null) {
                weatherBuffer.append(weatherDataLine);
            }
            weatherStream.close();
            weatherConnection.disconnect();

            return weatherBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                weatherStream.close();
            } catch(Throwable t) {}
            try {
                weatherConnection.disconnect();
            } catch(Throwable t) {}
        }

        return null;
    }

    /**
     * The getWeatherIcon method creates a URL connection, HttpURLConnection, and an InputStream
     * The HttpURLConnection accepts the URL variable and opens the connection and input is set
     * to true and the HttpURLconnection is established
     * A Bitmap accepts the bit values of an image and stores the values in a usable
     * format so the image can pass to the ImageView component on the fragment_weather_forecast
     * layout and the method will return the Bitmap value obtained from the InputStream
     * @param icon
     * @return
     */
    public Bitmap getWeatherIcon(String icon) {

        try {
            URL
                    iconConnection = new URL(icon);
            HttpURLConnection
                    weatherIconConnection = (HttpURLConnection) iconConnection.openConnection();
            weatherIconConnection.setDoInput(true);
            weatherIconConnection.connect();
            InputStream
                    iconStream = weatherIconConnection.getInputStream();
            Bitmap
                    weatherIconBitmap = BitmapFactory.decodeStream(iconStream);
            return weatherIconBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
