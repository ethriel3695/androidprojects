package com.elllistech.dailyworkout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Author: Reuben Ellis
 * Date: 10/01/2016
 * This fragment is used to display all activities saved within the default sharedPreferences
 * of the application
 */
public class ActivityDisplayFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActivityDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityDisplayFragment.
     */
    public static ActivityDisplayFragment newInstance(String param1, String param2) {
        ActivityDisplayFragment fragment = new ActivityDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_display, container, false);
    }

    /**
     * The onViewCreated method fires after the fragment creation is complete
     * The method checks for how many records are in the sharedPreferences and
     * creates a for loop to iterate through the sharedPreferences records
     * Table rows and a text field for each activity detail are created dynamically within code
     * The layout is created within code so the table only has as many rows as records
     * within the sharedPreferences file
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //ActivityDisplayFragment context = this;
        SharedPreferences
            activityPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int
            sizeOfPreference = activityPreferences.getAll().size();

        if (sizeOfPreference != 0)
        {
            int
                preferenceLength = sizeOfPreference / 3;
            for (int i = 1; i <= preferenceLength; i += 1)
            {
                String
                        activity = activityPreferences.getString("activity" + i, ""),
                        date = activityPreferences.getString("date" + i, "");
                int
                        miles = activityPreferences.getInt("miles" + i, 0);

                String
                        milesText = String.valueOf(miles);

                TableLayout
                        tblWorkout = (TableLayout)view.findViewById(R.id.fragDisplayActivity);

                TableRow
                        workoutRow = new TableRow(getContext());
                workoutRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                TextView
                        txtActivityDisplay = new TextView(getContext());
                txtActivityDisplay.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 25));
                txtActivityDisplay.setGravity(Gravity.CENTER);
                txtActivityDisplay.setTextColor(Color.BLACK);
                txtActivityDisplay.setTextSize(16);
                txtActivityDisplay.setPadding(10, 10, 10, 10);
                txtActivityDisplay.setText(activity);
                workoutRow.addView(txtActivityDisplay);

                TextView
                        txtMilesDisplay = new TextView(getContext());
                txtMilesDisplay.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 25));
                txtMilesDisplay.setGravity(Gravity.CENTER);
                txtMilesDisplay.setTextColor(Color.BLACK);
                txtMilesDisplay.setTextSize(16);
                txtMilesDisplay.setPadding(10, 10, 10, 10);
                txtMilesDisplay.setText(milesText);
                workoutRow.addView(txtMilesDisplay);
                TextView
                        txtDateDisplay = new TextView(getContext());
                txtDateDisplay.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 25));
                txtDateDisplay.setGravity(Gravity.CENTER);
                txtDateDisplay.setTextColor(Color.BLACK);
                txtDateDisplay.setTextSize(16);
                txtDateDisplay.setPadding(10, 10, 10, 10);
                txtDateDisplay.setText(date);
                workoutRow.addView(txtDateDisplay);

                tblWorkout.addView(workoutRow);
            }
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
