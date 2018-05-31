package com.example.raydel.substantialsubs.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raydel.substantialsubs.R;

import lombok.NoArgsConstructor;


/**
 * A simple {@link Fragment} subclass.
 */

@NoArgsConstructor
public class NewOrderFragment extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_new_order, container, false);
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