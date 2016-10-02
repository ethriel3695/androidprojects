package com.elllistech.dailyworkout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Author: Reuben Ellis
 * Date: 10/01/2016
 * This daily workout application provides the user with the ability to enter
 * an activity name, number of miles, and date of the activity within EditText Views
 * The class also implements a fragment listener so a fragment can be used to load
 * the activities saved within sharedPreferences at the bottom of the activity layout
 */
public class DailyWorkoutEntryActivity extends AppCompatActivity
        implements ActivityDisplayFragment.OnFragmentInteractionListener {
    String
        currentActivity = "",
        numberOfMilesText = "",
        activityDate = "";
    int
        numberOfMiles = 0,
        preferenceCount = 0,
        sizeOfPreferences = 0;

    @Override
    /**
     * The method creates the activity layout through the setContentView method
     * Shared preferences, text views, and event listeners all appear within the onCreate method
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_workout_entry);

        final Context
                context = this;

        final SharedPreferences
                savedActivities = PreferenceManager.getDefaultSharedPreferences(context);

        //Instantiates the fragment to replace the default layout with the fragment on
        //the main activity layout
        final ActivityDisplayFragment
                fragment = new ActivityDisplayFragment();
        FragmentManager
                fragmentManager = getSupportFragmentManager();
        FragmentTransaction
                fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.defaultLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        final EditText
                txtActivity = (EditText)findViewById(R.id.editTxtActivity),
                txtMiles = (EditText)findViewById(R.id.editTxtMiles),
                txtDate = (EditText)findViewById(R.id.editTxtDate);
        Button
                addActivity = (Button)findViewById(R.id.btnAddActivity);

        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeOfPreferences = savedActivities.getAll().size();

                if (sizeOfPreferences != 0 && sizeOfPreferences >= 3)
                {
                    preferenceCount = (sizeOfPreferences / 3) + 1;
                }
                else if (sizeOfPreferences == 0)
                {
                    preferenceCount = 1;
                }

                currentActivity = txtActivity.getText().toString();
                numberOfMilesText = txtMiles.getText().toString();

                if (!numberOfMilesText.equals(""))
                {
                    numberOfMiles = Integer.parseInt(txtMiles.getText().toString());
                }
                activityDate = txtDate.getText().toString();

                if (!currentActivity.equals("") && !numberOfMilesText.equals("") &&
                        !activityDate.equals(""))
                {
                    SharedPreferences.Editor
                            activityDisplay = savedActivities.edit();
                    activityDisplay.putString("activity" + preferenceCount, currentActivity);
                    activityDisplay.putInt("miles" + preferenceCount, numberOfMiles);
                    activityDisplay.putString("date" + preferenceCount, activityDate);
                    activityDisplay.commit();
                    FragmentManager
                            fragmentManager = getSupportFragmentManager();
                    FragmentTransaction
                            fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.detach(fragment);
                    fragmentTransaction.attach(fragment);
                    fragmentTransaction.commit();
                }
                else
                {
                    Toast.makeText(context, "Please enter an activity, \nnumber of miles and date!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
