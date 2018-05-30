package com.example.raydel.substantialsubs.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.raydel.substantialsubs.R;

import lombok.NoArgsConstructor;

import static com.example.raydel.substantialsubs.utils.Utils.currentOrder;
import static com.example.raydel.substantialsubs.utils.Utils.setInputText;

@NoArgsConstructor
public class CustomerDetailsFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_customer_details, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        setInputText(this.getActivity(), R.id.name_editText, currentOrder.getName());
        setInputText(this.getActivity(), R.id.phone_editText, currentOrder.getPhone());
        setInputText(this.getActivity(), R.id.address_editText, currentOrder.getAddress());
        setInputText(this.getActivity(), R.id.ccNumber_editText, currentOrder.getCcNumber());
        setInputText(this.getActivity(), R.id.ccExpMM_editText, currentOrder.getCcExpMM());
        setInputText(this.getActivity(), R.id.ccExpYYYY_editText, currentOrder.getCcExpYYYY());
        setInputText(this.getActivity(), R.id.ccCVV_editText, currentOrder.getCcCVV());

        if (currentOrder.isDelivery()){
            this.getActivity().findViewById(R.id.paymentMethod_textView).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.paymentMethod_radioGroup).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.address_editText).setVisibility(View.VISIBLE);

        }else {
            this.getActivity().findViewById(R.id.paymentMethod_textView).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.paymentMethod_radioGroup).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.address_editText).setVisibility(View.GONE);

            currentOrder.setCard(false);
        }

        if(!currentOrder.isCard()){
            RadioButton cash = (RadioButton) this.getActivity().findViewById(R.id.cash_radioButton);
            cash.setChecked(true);

            this.getActivity().findViewById(R.id.ccNumber_editText).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.ccExpMM_editText).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.ccExpYYYY_editText).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.ccCVV_editText).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.div_textView).setVisibility(View.GONE);

        }else {
            RadioButton cc = (RadioButton) this.getActivity().findViewById(R.id.creditCard_radioButton);
            cc.setChecked(true);

            this.getActivity().findViewById(R.id.ccNumber_editText).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.ccExpMM_editText).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.ccExpYYYY_editText).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.ccCVV_editText).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.div_textView).setVisibility(View.VISIBLE);
        }

        RadioGroup paymentMethod_radioGroup = (RadioGroup) this.getActivity().findViewById(R.id.paymentMethod_radioGroup);
        final Activity activity = this.getActivity();

        paymentMethod_radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        if (checkedId == R.id.cash_radioButton) {
                            activity.findViewById(R.id.ccNumber_editText).setVisibility(View.GONE);
                            activity.findViewById(R.id.ccExpMM_editText).setVisibility(View.GONE);
                            activity.findViewById(R.id.ccExpYYYY_editText).setVisibility(View.GONE);
                            activity.findViewById(R.id.ccCVV_editText).setVisibility(View.GONE);
                            activity.findViewById(R.id.div_textView).setVisibility(View.GONE);

                        } else if (checkedId == R.id.creditCard_radioButton) {
                            activity.findViewById(R.id.ccNumber_editText).setVisibility(View.VISIBLE);
                            activity.findViewById(R.id.ccExpMM_editText).setVisibility(View.VISIBLE);
                            activity.findViewById(R.id.ccExpYYYY_editText).setVisibility(View.VISIBLE);
                            activity.findViewById(R.id.ccCVV_editText).setVisibility(View.VISIBLE);
                            activity.findViewById(R.id.div_textView).setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public void setId(long id){
        this.id = id;
    }

}
