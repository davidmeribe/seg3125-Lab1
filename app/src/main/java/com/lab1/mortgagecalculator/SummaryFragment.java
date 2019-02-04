package com.lab1.mortgagecalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Currency;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERNAME = "username";
    private static final String ARG_AMOUNT = "amount";

    // TODO: Rename and change types of parameters
    private String username;
    private double amount;
    private String frequencySetting;
    private String currencySymbol;

    private OnFragmentInteractionListener mListener;

    public SummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username Parameter 1.
     * @param amount Parameter 2.
     * @return A new instance of fragment SummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    // arguments is going to be the amount calculated and displayed
    public static SummaryFragment newInstance(String username, double amount) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putDouble(ARG_AMOUNT, amount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
            amount = getArguments().getDouble(ARG_AMOUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getFrequencySetting();
        getCurrencySymbol();

        //Todo: append before sentence and after sentence
        String modifyText = username + " should make "+ frequencySetting +" payments of "
                + currencySymbol + amount +"!";

        TextView summaryInfo = (TextView) view.findViewById(R.id.summary_info);
        summaryInfo.setText(modifyText);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getFrequencySetting(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //get settings values to use in calculation
        frequencySetting = preferences.getString("paymentFrequency", "monthly");
    }

    private void getCurrencySymbol(){
        Locale locale = Locale.US;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //get settings values to use in calculation
        String currencyString = preferences.getString("currencySymbol", "dollar");

        if (currencyString.equals("dollar")){
            locale = Locale.US;
        }else if (currencyString.equals("pound")){
            locale = Locale.UK;
        }else if (currencyString.equals("euro")){
            locale = Locale.GERMANY;
        }

        currencySymbol = Currency.getInstance(locale).getSymbol();
    }

}
