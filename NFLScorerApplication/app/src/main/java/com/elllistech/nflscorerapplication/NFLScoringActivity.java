package com.elllistech.nflscorerapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.R.attr.id;

public class NFLScoringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nflscoring);

        ActionBar
                titleBar = getSupportActionBar();

        titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        Spinner
                eastTeamsSpinner = (Spinner)findViewById(R.id.spinnerEastTeams),
                westTeamsSpinner = (Spinner)findViewById(R.id.spinnerWestTeams);

        ArrayAdapter<CharSequence>
                westTeamsAdapter = ArrayAdapter.createFromResource(this, R.array.westTeams,
                    R.layout.support_simple_spinner_dropdown_item),
                eastTeamsAdapter = ArrayAdapter.createFromResource(this, R.array.eastTeams,
                    R.layout.support_simple_spinner_dropdown_item);
        westTeamsSpinner.setAdapter(westTeamsAdapter);
        eastTeamsSpinner.setAdapter(eastTeamsAdapter);

        //TODO: Create a fragment which populates the Score if the teams played
        //TODO: Create a fragment which populates the text "DID NOT PLAY" if the teams did not play
//        String[]
//            eastTeams = {"New England Patriots", "Green Bay Packers",
//                "Chicago Bears", "Minnesota Vikings"},
//            westTeams = {"Denver Broncos", "Seattle Seahawks",
//                    "Los Angeles Rams", "Arizona Cardinals"};



    }
}
