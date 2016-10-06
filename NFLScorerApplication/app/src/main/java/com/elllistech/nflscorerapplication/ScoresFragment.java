package com.elllistech.nflscorerapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoresFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoresFragment.
     */
    public static ScoresFragment newInstance(String param1, String param2) {
        ScoresFragment fragment = new ScoresFragment();
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

            container.clearDisappearingChildren();
            return inflater.inflate(R.layout.fragment_scores, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        SharedPreferences
                activityPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String
                eastTeam = activityPreferences.getString("currentEastTeam", ""),
                westTeam = activityPreferences.getString("currentWestTeam", ""),
                eastFinalScore = "",
                westFinalScore = "";

        int
            westFinalInt = 0,
            eastFinalInt = 0;

        if (!eastTeam.equals("Default") || !westTeam.equals("Default"))
        {
            TextView
                    east = (TextView)view.findViewById(R.id.txtEastTeam),
                    west = (TextView)view.findViewById(R.id.txtWestTeam),
                    westQ1 = (TextView)view.findViewById(R.id.txtWestQ1Score),
                    westQ2 = (TextView)view.findViewById(R.id.txtWestQ2Score),
                    westQ3 = (TextView)view.findViewById(R.id.txtWestQ3Score),
                    westQ4 = (TextView)view.findViewById(R.id.txtWestQ4Score),
                    eastQ1 = (TextView)view.findViewById(R.id.txtEastQ1Score),
                    eastQ2 = (TextView)view.findViewById(R.id.txtEastQ2Score),
                    eastQ3 = (TextView)view.findViewById(R.id.txtEastQ3Score),
                    eastQ4 = (TextView)view.findViewById(R.id.txtEastQ4Score),
                    westFinal = (TextView)view.findViewById(R.id.txtWestFinalScore),
                    eastFinal = (TextView)view.findViewById(R.id.txtEastFinalScore),
                    txtDidNotPlay = (TextView)view.findViewById(R.id.txtDidNotPlay);

            east.setText(eastTeam);
            west.setText(westTeam);

            if (eastTeam.equals("Seattle Seahawks") && westTeam.equals("Los Angeles Rams"))
            {
                eastQ1.setText("0");
                eastQ2.setText("3");
                eastQ3.setText("0");
                eastQ4.setText("0");
                eastFinal.setText("3");

                westQ1.setText("3");
                westQ2.setText("3");
                westQ3.setText("0");
                westQ4.setText("3");
                westFinal.setText("9");

                txtDidNotPlay.setText("");

                eastFinalScore = eastFinal.getText().toString();
                westFinalScore = westFinal.getText().toString();

                eastFinalInt = Integer.parseInt(eastFinalScore);
                westFinalInt = Integer.parseInt(westFinalScore);

                if (westFinalInt > eastFinalInt) {
                    westFinal.setTextColor(Color.GREEN);
                    westFinal.setTextSize(18);
                }
                else if (westFinalInt < eastFinalInt) {
                    eastFinal.setTextColor(Color.GREEN);
                    eastFinal.setTextSize(18);
                }
            }
            else if (eastTeam.equals("Los Angeles Rams") && westTeam.equals("Seattle Seahawks"))
            {
                eastQ1.setText("3");
                eastQ2.setText("3");
                eastQ3.setText("0");
                eastQ4.setText("3");
                eastFinal.setText("9");

                westQ1.setText("0");
                westQ2.setText("3");
                westQ3.setText("0");
                westQ4.setText("0");
                westFinal.setText("3");

                txtDidNotPlay.setText("");

                eastFinalScore = eastFinal.getText().toString();
                westFinalScore = westFinal.getText().toString();

                eastFinalInt = Integer.parseInt(eastFinalScore);
                westFinalInt = Integer.parseInt(westFinalScore);

                if (westFinalInt > eastFinalInt) {
                    westFinal.setTextColor(Color.GREEN);
                    westFinal.setTextSize(18);
                }
                else if (westFinalInt < eastFinalInt) {
                    eastFinal.setTextColor(Color.GREEN);
                    eastFinal.setTextSize(18);
                }
            }
            else if (eastTeam.equals("Green Bay Packers") && westTeam.equals("Minnesota Vikings"))
            {
                eastQ1.setText("7");
                eastQ2.setText("0");
                eastQ3.setText("0");
                eastQ4.setText("7");
                eastFinal.setText("14");

                westQ1.setText("0");
                westQ2.setText("10");
                westQ3.setText("7");
                westQ4.setText("0");
                westFinal.setText("17");

                txtDidNotPlay.setText("");

                eastFinalScore = eastFinal.getText().toString();
                westFinalScore = westFinal.getText().toString();

                eastFinalInt = Integer.parseInt(eastFinalScore);
                westFinalInt = Integer.parseInt(westFinalScore);

                if (westFinalInt > eastFinalInt) {
                    westFinal.setTextColor(Color.GREEN);
                    westFinal.setTextSize(18);
                }
                else if (westFinalInt < eastFinalInt) {
                    eastFinal.setTextColor(Color.GREEN);
                    eastFinal.setTextSize(18);
                }
            }
            else if (eastTeam.equals("Minnesota Vikings") && westTeam.equals("Green Bay Packers"))
            {
                eastQ1.setText("0");
                eastQ2.setText("10");
                eastQ3.setText("7");
                eastQ4.setText("0");
                eastFinal.setText("17");

                westQ1.setText("7");
                westQ2.setText("0");
                westQ3.setText("0");
                westQ4.setText("7");
                westFinal.setText("14");

                txtDidNotPlay.setText("");

                eastFinalScore = eastFinal.getText().toString();
                westFinalScore = westFinal.getText().toString();

                eastFinalInt = Integer.parseInt(eastFinalScore);
                westFinalInt = Integer.parseInt(westFinalScore);

                if (westFinalInt > eastFinalInt) {
                    westFinal.setTextColor(Color.GREEN);
                    westFinal.setTextSize(18);
                }
                else if (westFinalInt < eastFinalInt) {
                    eastFinal.setTextColor(Color.GREEN);
                    eastFinal.setTextSize(18);
                }
            }
            else
            {
            }
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
