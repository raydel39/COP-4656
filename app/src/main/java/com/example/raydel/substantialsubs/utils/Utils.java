package com.example.raydel.substantialsubs.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raydel.substantialsubs.R;
import com.example.raydel.substantialsubs.fragments.CustomerDetailsFragment;
import com.example.raydel.substantialsubs.fragments.MenuFragment;
import com.example.raydel.substantialsubs.fragments.MenuItemsFragment;
import com.example.raydel.substantialsubs.fragments.NewOrderFragment;
import com.example.raydel.substantialsubs.fragments.PreviewFragment;
import com.example.raydel.substantialsubs.model.MenuItem;
import com.example.raydel.substantialsubs.model.Order;

import java.util.ArrayList;

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
    public static Fragment currFragment;
    public static Fragment navFragment;
    public static Initializer dataInit = new Initializer();

    public static void changeFragment(FragmentTransaction ft, int id, Fragment fragment){

        // replace the fragment
        ft.replace(id, fragment);

        // !!!! Add to the backstack
       ft.addToBackStack(null);

        // get the old fragment and the new to fade in/out
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        // commit
        ft.commit();

    }

    //Method to handle forward navigation between fragments
    public static void handleForwardNavigation(Activity activity, Fragment currentfragment){

        if(currentfragment instanceof NewOrderFragment){
            changeFragment(activity.getFragmentManager().beginTransaction(),R.id.fragment_main, currentfragment);
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            navFragment.getView().findViewById(R.id.back_button).setVisibility(View.GONE);
            ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("BACK");
            ((Button) navFragment.getView().findViewById(R.id.next_button)).setText("NEXT");

            currFragment = currentfragment;

        } else if(currentfragment instanceof CustomerDetailsFragment){
            MenuFragment menuFragment = new MenuFragment();
            currentOrder.setName(getInputText(activity, R.id.name_editText));
            currentOrder.setPhone(getInputText(activity, R.id.phone_editText));

            if(currentOrder.isDelivery()) {
                currentOrder.setAddress(getInputText(activity, R.id.address_editText));
                currentOrder.setCcNumber(getInputText(activity, R.id.ccNumber_editText));
                currentOrder.setCcExpMM(getInputText(activity, R.id.ccExpMM_editText));
                currentOrder.setCcExpYYYY(getInputText(activity, R.id.ccExpYYYY_editText));
                currentOrder.setCcCVV(getInputText(activity, R.id.ccCVV_editText));

                RadioGroup paymentMethod = (RadioGroup) activity.findViewById(R.id.paymentMethod_radioGroup);

                switch (paymentMethod.getCheckedRadioButtonId()) {
                    case R.id.cash_radioButton:
                        currentOrder.setCard(false);
                        break;
                    case R.id.creditCard_radioButton:
                        currentOrder.setCard(true);
                        break;
                    default:
                        Toast.makeText(activity, "Invalid Payment Method", Toast.LENGTH_SHORT).show();
                        return;
                }

                validateAddress(activity);
            } else {
                changeFragment(activity.getFragmentManager().beginTransaction(),R.id.fragment_main, menuFragment);
                currFragment = menuFragment;
                navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            }

        } else if(currentfragment instanceof MenuItemsFragment){
            PreviewFragment previewFragment = new PreviewFragment();
            changeFragment(activity.getFragmentManager().beginTransaction(),R.id.fragment_main, previewFragment);
            currFragment = previewFragment;
            ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("EDIT");
            ((Button) navFragment.getView().findViewById(R.id.next_button)).setText("SUBMIT");

            activity.getFragmentManager().executePendingTransactions();

            ((TextView) currFragment.getView().findViewById(R.id.previewOrderTextview)).setText(Html.fromHtml(currentOrder.toString(),Html.FROM_HTML_MODE_COMPACT));

        }else if(currentfragment instanceof PreviewFragment){
            Toast.makeText(activity, "Your order was submitted it!", Toast.LENGTH_SHORT).show();

            currentOrder = new Order();
            NewOrderFragment orderFragment = new NewOrderFragment();
            changeFragment(activity.getFragmentManager().beginTransaction(),R.id.fragment_main, orderFragment);

            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            navFragment.getView().findViewById(R.id.back_button).setVisibility(View.GONE);
            ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("BACK");
            ((Button) navFragment.getView().findViewById(R.id.next_button)).setText("NEXT");
        }
    }

    //Method to handle forward navigation between fragments
    public static void handleBackwardNavigation(Activity activity, Fragment currentfragment){
        Fragment fragment = null;

        if(currentfragment instanceof CustomerDetailsFragment){
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            navFragment.getView().findViewById(R.id.back_button).setVisibility(View.GONE);
            fragment = new NewOrderFragment();

            currentOrder.setName(getInputText(activity, R.id.name_editText));
            currentOrder.setPhone(getInputText(activity, R.id.phone_editText));

            if(currentOrder.isDelivery()) {
                currentOrder.setAddress(getInputText(activity, R.id.address_editText));
                currentOrder.setCcNumber(getInputText(activity, R.id.ccNumber_editText));
                currentOrder.setCcExpMM(getInputText(activity, R.id.ccExpMM_editText));
                currentOrder.setCcExpYYYY(getInputText(activity, R.id.ccExpYYYY_editText));
                currentOrder.setCcCVV(getInputText(activity, R.id.ccCVV_editText));

                RadioGroup paymentMethod = (RadioGroup) activity.findViewById(R.id.paymentMethod_radioGroup);

                switch (paymentMethod.getCheckedRadioButtonId()) {
                    case R.id.cash_radioButton:
                        currentOrder.setCard(false);
                        break;
                    case R.id.creditCard_radioButton:
                        currentOrder.setCard(true);
                        break;
                    default:
                        Toast.makeText(activity, "Invalid Payment Method", Toast.LENGTH_SHORT).show();
                        return;
                }
            }

        } else if(currentfragment instanceof MenuFragment){
            fragment = new CustomerDetailsFragment();
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);

        } else if(currentfragment instanceof MenuItemsFragment){
            fragment = new MenuFragment();
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("BACK");

        }else if(currentfragment instanceof PreviewFragment){
            fragment = new NewOrderFragment();
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
            navFragment.getView().findViewById(R.id.back_button).setVisibility(View.GONE);
            ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("BACK");
            ((Button) navFragment.getView().findViewById(R.id.next_button)).setText("NEXT");
        }


        currFragment = fragment;
        changeFragment(activity.getFragmentManager().beginTransaction(),R.id.fragment_main, fragment);
    }

    public static double handleSelectedItems(StringBuilder html){

        double total = 0;

        total += handleSingleList(dataInit.getBreakfastItems(),html);
        total += handleSingleList(dataInit.getLunchItems(),html);
        total += handleSingleList(dataInit.getSidesItems(),html);
        total += handleSingleList(dataInit.getDrinksItems(),html);
        total += handleSingleList(dataInit.getDessertItems(),html);

        return total;
    }

    private static double handleSingleList(ArrayList<MenuItem> menuItems, StringBuilder html){

        double total = 0;

        for(MenuItem menuItem : menuItems){
            if(menuItem.getQuantity() > 0){
                html.append(menuItem.toString())
                    .append("<br>");

                total += menuItem.getPrice();
            }
        }

        return total;
    }

    public static void menuItem_onCLick (Activity activity, ArrayList<MenuItem> menuItems){
        Fragment fragment = MenuItemsFragment.newInstance(menuItems);
        changeFragment(activity.getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
        ((Button) navFragment.getView().findViewById(R.id.back_button)).setText("SELECT OTHER MENU ITEMS");
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

        MenuFragment menuFragment = new MenuFragment();

        if (currentOrder.getDistanceToAddress() > 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle("Address Out of Range")
                    .setMessage("Address is " + currentOrder.getDistanceToAddress() + " away. Would you like to continue?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        changeFragment(activity. getFragmentManager().beginTransaction(),R.id.fragment_main, menuFragment);
                        currFragment = menuFragment;
                        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
                    })
                    .setNegativeButton(android.R.string.no, (dialog, which) -> { })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            changeFragment(activity. getFragmentManager().beginTransaction(),R.id.fragment_main, menuFragment);
            currFragment = menuFragment;
            navFragment.getView().findViewById(R.id.next_button).setVisibility(View.GONE);
        }
    }

    private static String makeLocationURL (String destination){

        return LOCATION_API_BASE_URL + URL_DIVIDER +
                URL_ORIGIN + SUBS_ADDRESS + URL_DIVIDER +
                URL_DESTINATION + destination + URL_DIVIDER +
                API_KEY;
    }

}
