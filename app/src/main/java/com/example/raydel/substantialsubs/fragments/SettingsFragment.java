package com.example.raydel.substantialsubs.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raydel.substantialsubs.CalendarTools;
import com.example.raydel.substantialsubs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.NoArgsConstructor;


/**
 * A simple {@link Fragment} subclass.
 */
@NoArgsConstructor
public class SettingsFragment extends Fragment {

    private final String TAG = "DEBUG";
    private long id;
    private static View view;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("id", id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            id = savedInstanceState.getLong("id");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        setDateTextView(CalendarTools.CurrentCalendar);

    }

    public void setId(long id){
        this.id = id;
    }

    public static void setDateTextView (Calendar calendar) {

        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        String date = df.format(calendar.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayOfWeek = sdf.format(calendar.getTime());

//        TextView dateTextView = (TextView) view.findViewById(R.id.dateTextView_night);
//        dateTextView_nighteTextView.setText(date);

//        TextView dayOfWeekTextView = (TextView) view.findViewById(R.id.dayOftheWeekTextView_night);
//        dayOfWeekTextView.setText(dayOfWeek);




    }

}
