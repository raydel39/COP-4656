package com.example.raydel.substantialsubs.fragments;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.raydel.substantialsubs.CalendarTools;
import com.example.raydel.substantialsubs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RangeFragment extends Fragment implements View.OnClickListener {

    private  View view;
    private EditText startDate, endDate;
    private DatePickerDialog startDateDialog, endDateDialog;
    private SimpleDateFormat df;

    public RangeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_range, container, false);
        df = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);

        findViewsById();
        setDateTimeField();
        return view;
    }

    private void setDateTimeField() {
        Context context = getContext();
        Calendar calendar = Calendar.getInstance();

        startDate.setOnClickListener(this);
        startDateDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDate.setText(df.format(newDate.getTime()));
                CalendarTools.range_StartDate = newDate;
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        endDate.setOnClickListener(this);
        endDateDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDate.setText(df.format(newDate.getTime()));
                CalendarTools.range_EndDate = newDate;
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void findViewsById() {
        startDate = (EditText) view.findViewById(R.id.range_startDate);
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.requestFocus();

        endDate = (EditText) view.findViewById(R.id.range_endDate);
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.requestFocus();
    }


    @Override
    public void onClick(View v) {
        if(v == startDate) {
            startDateDialog.show();
        }
        else if(v == endDate) {
            endDateDialog.show();
        }
    }
}

