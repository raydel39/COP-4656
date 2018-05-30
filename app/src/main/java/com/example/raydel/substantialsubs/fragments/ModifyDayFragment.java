package com.example.raydel.substantialsubs.fragments;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.raydel.substantialsubs.CalendarTools;
import com.example.raydel.substantialsubs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyDayFragment extends Fragment {

    private final String TAG = "DEBUG";
    private long id;
    private static View view;

    public ModifyDayFragment() {
        // Required empty public constructor
    }


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
        view = inflater.inflate(R.layout.fragment_modify_day, container, false);
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

        TextView dateTextView = (TextView) view.findViewById(R.id.dateTextView_modify);
        dateTextView.setText(date);

        TextView dayOfWeekTextView = (TextView) view.findViewById(R.id.dayOftheWeekTextView_modify);
        dayOfWeekTextView.setText(dayOfWeek);

        if(dayOfWeek.equals("Sunday"))
        {
            TextView txt = (TextView) view.findViewById(R.id.textViewModifyNight);
            txt.setText("There is NO night sales on Sundays");
            txt.setTextColor(Color.RED);

            EditText sales = (EditText) view.findViewById(R.id.modify_nightSale);
            sales.setEnabled(false);

            EditText ccSales = (EditText) view.findViewById(R.id.modify_nightCCSales);
            ccSales.setEnabled(false);

            EditText ccAmount = (EditText) view.findViewById(R.id.modify_nightCCAmount);
            ccAmount.setEnabled(false);
        }
    }

}
