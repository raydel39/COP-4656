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
public class PickDayModifyFragment extends Fragment implements View.OnClickListener {

    private  View view;
    private EditText selectDateEditText;
    private DatePickerDialog selectDateDialog;
    private SimpleDateFormat df;

    public PickDayModifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pick_day_modify, container, false);
        df = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);

        findViewsById();
        setDateTimeField();

        return view;
    }

    private void setDateTimeField() {
        Context context = getContext();
        selectDateEditText.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();

        selectDateDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                selectDateEditText.setText(df.format(newDate.getTime()));
                CalendarTools.CurrentCalendar = newDate;
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void findViewsById() {
        selectDateEditText = (EditText) view.findViewById(R.id.modify_selectedDay);
        selectDateEditText.setInputType(InputType.TYPE_NULL);
        selectDateEditText.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if(v == selectDateEditText) {
            selectDateDialog.show();
        }
    }
}

