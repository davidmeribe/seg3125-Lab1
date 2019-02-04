package com.lab1.mortgagecalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WelcomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAYMENTS = "payments";
    private static final String ARG_USERNAME = "username";

    // TODO: Rename and change types of parameters
    private String username;
    private double principal;
    private double interest;
    private int amortization;
    private double payments;
    private  int frequencyPerYear;
    //private double mortgage;
    private String currency;
    private Locale locale;

    private OnFragmentInteractionListener mListener;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username Parameter 1.
     * @param payments Parameter 2.
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String username, String payments) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PAYMENTS, username);
        args.putString(ARG_USERNAME, payments);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            payments = getArguments().getDouble(ARG_PAYMENTS);
            username = getArguments().getString(ARG_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        //set on click for buttons
        Button calculate = (Button) v.findViewById(R.id.calculate_btn);
        calculate.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
         super.onViewCreated(view,savedInstanceState);
        //get currency setting
         getCurrencySetting();


        //register listeners
         EditText name = (EditText) view.findViewById(R.id.username);
            name.addTextChangedListener(new TextValidator(name) {
                @Override
                public void validate(TextView textView) {
                    if(textView.getText().toString().length() == 0){
                        textView.setError(textView.getHint() +" is required!");
                    }
                }
            });
            name.setOnFocusChangeListener(new TextValidator(name) {
                @Override
                public void validate(TextView textView) {
                    if(textView.getText().toString().length() == 0){
                        textView.setError(textView.getHint() +" is required!");
                    }
                }
            });
      //==============================

        final EditText principl = (EditText) view.findViewById(R.id.principal);
            principl.addTextChangedListener(new TextValidator(principl) {
                @Override
                public void validate(TextView textView) {
                    // format currency first
                    if (!mEditing) {
                        mEditing = true;

                        String digits = textView.getText().toString().replaceAll("\\D", "");
                        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

                        try{
                            String formatted = nf.format(Double.parseDouble(digits)/100);
                            //textView.append(formatted);
                            textView.setText(formatted);
                            //put cursor at end
                            principl.setSelection(principl.getText().length());
                        }catch (NumberFormatException nfe){
                            textView.clearComposingText();
                        }
                        mEditing = false;
                    }
                    if(textView.getText().toString().length() == 0){
                        textView.setError(textView.getHint() +" is required!");
                    }
                }
            });
            principl.setOnFocusChangeListener(new TextValidator(principl) {
                @Override
                public void validate(TextView textView) {
                    if(textView.getText().toString().length() == 0){
                        textView.setError(textView.getHint() +" is required!");
                    }
                }
            });
      //================================

        EditText amortizatn = (EditText) view.findViewById(R.id.amortization);
               amortizatn.addTextChangedListener(new TextValidator(amortizatn) {
                   @Override
                   public void validate(TextView textView) {
                       if(textView.getText().toString().length() == 0){
                           textView.setError(textView.getHint() +" is required!");
                       }
                   }
               });
        EditText interst = (EditText) view.findViewById(R.id.interest);
             interst.addTextChangedListener(new TextValidator(interst) {
                 @Override
                 public void validate(TextView textView) {
                     if(textView.getText().toString().length() == 0){
                         textView.setError(textView.getHint() +" is required!");
                     }
                 }
             });



    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.calculate_btn:
                calculateMortgage(view);
                break;

            case R.id.reset_btn:
                clearForm(view);
                break;

            default:
                break;
        }
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

    public void clearForm(View view){

    }

    public void calculateMortgage(View view){
        //Calculate the mortgage and send to the summary fragment
        EditText usr = (EditText) getView().findViewById(R.id.username);
              username =  usr.getText().toString();

        EditText principl = (EditText) getView().findViewById(R.id.principal);
        try {
            principal = Double.parseDouble(principl.getText().toString()); // try and catch exception
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
         getFrequencyPerYear();  //get frequency per year

        EditText intrst = (EditText) getView().findViewById(R.id.interest);
          interest = Double.parseDouble(intrst.getText().toString());

          EditText amrt = (EditText) getView().findViewById(R.id.amortization);
           amortization = Integer.parseInt(amrt.getText().toString());

           payments = mortgage(principal,interest,amortization);

           //save payments and open summary page
           saveData();

           goToSummary();
    }

    public void goToSummary(){

        /*if(getActivity() != null){
            ft = getActivity().getSupportFragmentManager().beginTransaction();
        }*/
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        SummaryFragment summaryFragment = SummaryFragment.newInstance(username,payments);
        ft.replace(R.id.mainContent,summaryFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void verifyForm(){
        //verify the data first
        //get  preferences for calculations
        //make calculations
        //update preferences

    }

    private  void saveData(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Username",username);
        editor.putFloat("Payments", (float) payments);
        editor.apply();
        //System.out.println()
    }

    private double mortgage(double principal, double interest, int amortization){
         double r = (interest/100)/12;
         double n = (frequencyPerYear * 12) * amortization;
        double numerator = r * Math.pow((1 + r),n);
        double denom = Math.pow((1 + r),n) - 1;

        double mortgage = principal * (numerator/denom);

        return mortgage;
    }

    private void getFrequencyPerYear(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //get settings values to use in calculation
        frequencyPerYear = preferences.getInt("paymentFrequencyPerYear", 1);

    }

    private void getCurrencySetting(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //get settings for currency
        currency = preferences.getString("currency", "US");

        if (currency.equals("US")){
            locale = Locale.US;
        }else if (currency.equals("UK")){
            locale = Locale.UK;
        }else if (currency.equals("GERMANY")){
            locale = Locale.GERMANY;
        }

    }


    public abstract class TextValidator implements TextWatcher, View.OnFocusChangeListener {
        private final TextView textView;
        boolean mEditing; // for CurrencyWatcher
        private String previousAmount = "";

        public TextValidator(TextView textView) {
            this.textView = textView;
            mEditing = false;
        }

        public abstract void validate(TextView textView);

        @Override
        final public void afterTextChanged(Editable s) {
            //String text = textView.getText().toString();
            validate(textView);
        }

        final public void onFocusChange(View textView, boolean hasFocus){
            if(!hasFocus){
                validate((TextView) textView);
            }
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            /* Don't care */

        }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }

}
