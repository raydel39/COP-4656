package com.example.raydel.substantialsubs.fragments;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.raydel.substantialsubs.R;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment implements View.OnClickListener {

    private  View view;
    private EditText selectDateEditText;
    private DatePickerDialog selectDateDialog;
    private SimpleDateFormat df;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_summary, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == selectDateEditText) {
            selectDateDialog.show();
        }
    }
}

