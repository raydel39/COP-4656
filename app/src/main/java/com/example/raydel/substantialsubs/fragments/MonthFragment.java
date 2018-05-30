package com.example.raydel.substantialsubs.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raydel.substantialsubs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment {

    private final String TAG = "DEBUG";
    private long id;
    private static View view;

    public MonthFragment() {
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
        view = inflater.inflate(R.layout.fragment_month, container, false);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public void setId(long id){
        this.id = id;
    }
}
