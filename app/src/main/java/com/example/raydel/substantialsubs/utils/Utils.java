package com.example.raydel.substantialsubs.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.example.raydel.substantialsubs.R;
import com.example.raydel.substantialsubs.fragments.MenuFragment;
import com.example.raydel.substantialsubs.model.Order;

import static com.example.raydel.substantialsubs.constants.SubstantialConstants.API_KEY;
import static com.example.raydel.substantialsubs.constants.SubstantialConstants.LOCATION_API_BASE_URL;
import static com.example.raydel.substantialsubs.constants.SubstantialConstants.SUBS_ADDRESS;
import static com.example.raydel.substantialsubs.constants.SubstantialConstants.URL_DESTINATION;
import static com.example.raydel.substantialsubs.constants.SubstantialConstants.URL_DIVIDER;
import static com.example.raydel.substantialsubs.constants.SubstantialConstants.URL_ORIGIN;

/**
 * Created by raydelmesa on 5/7/18.
 */

public class Utils {

    public static Order currentOrder;

    public static void changeFragment(FragmentTransaction ft, int id, Fragment fragment){

        // replace the fragment
        ft.replace(R.id.fragment_main, fragment);

        // !!!! Add to the backstack
        ft.addToBackStack(null);

        // get the old fragment and the new to fade in/out
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        // commit
        ft.commit();
    }

    public static String getInputText (Activity activity, int id){
        EditText editText = (EditText) activity.findViewById(id);
        return editText.getText().toString();
    }

    public static void setInputText (Activity activity, int id, String text){
        EditText editText = (EditText) activity.findViewById(id);
        editText.setText(text);
    }

    public static void validateAddress(final Activity activity) {

        String url = makeLocationURL(currentOrder.getAddress());
        Connection.get(activity, url);
    }

    public static void checkDistance(final Activity activity){

        if (currentOrder.getDistanceToAddress() > 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle("Address Out of Range")
                    .setMessage("Address is " + currentOrder.getDistanceToAddress() + " away. Would you like to continue?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            changeFragment(activity. getFragmentManager().beginTransaction(),R.id.fragment_main, new MenuFragment());
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            EditText address = (EditText) activity.findViewById(R.id.address_editText);
                            address.setText("");
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            changeFragment(activity. getFragmentManager().beginTransaction(),R.id.fragment_main, new MenuFragment());
        }
    }

    private static String makeLocationURL (String destination){

        return LOCATION_API_BASE_URL + URL_DIVIDER +
                URL_ORIGIN + SUBS_ADDRESS + URL_DIVIDER +
                URL_DESTINATION + destination + URL_DIVIDER +
                API_KEY;
    }

}
