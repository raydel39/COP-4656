package com.example.raydel.substantialsubs;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raydel.substantialsubs.fragments.CustomerDetailsFragment;
import com.example.raydel.substantialsubs.fragments.MenuItemsFragment;
import com.example.raydel.substantialsubs.fragments.NavigationFragment;
import com.example.raydel.substantialsubs.fragments.NewOrderFragment;
import com.example.raydel.substantialsubs.fragments.SettingsFragment;
import com.example.raydel.substantialsubs.model.Order;
import com.example.raydel.substantialsubs.utils.Initializer;
import com.example.raydel.substantialsubs.utils.Utils;

import static com.example.raydel.substantialsubs.utils.Utils.changeFragment;
import static com.example.raydel.substantialsubs.utils.Utils.currFragment;
import static com.example.raydel.substantialsubs.utils.Utils.currentOrder;
import static com.example.raydel.substantialsubs.utils.Utils.handleBackwardNavigation;
import static com.example.raydel.substantialsubs.utils.Utils.handleForwardNavigation;
import static com.example.raydel.substantialsubs.utils.Utils.navFragment;


//--------------------------------------------------------------------------
// Author            :  Raydel Mesa
// Date              :
//--------------------------------------------------------------------------
// Revision          :  1.0
// Dependencies      :  lombok. Make sure pre processor annotations are enabled on the IDE as well
// Description       :  Phone Order Processing for substantial subs
// Simulation Notes  :  Please use Nexus 10, Android 8.0.0 or any other table simulator
// Synthesis Notes   :
// Application Notes :
// Simulator         :  Nexus 10, Android 8.0.0
// Parameters        :
//--------------------------------------------------------------------------
// Revision History  :
//--------------------------------------------------------------------------
//  Date             : Change
//--------------------------------------------------------------------------
//  07/14/2018       : Initialing Menu Items
//  07/15/2018       : Adding List fragments for menu items
//  07/17/2018       : User can now select the amount of items they want from the menu
//  07/22/2018       : Adding Navigation between fragments

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MenuItemsFragment.OnListFragmentInteractionListener {
    public static Context context;

    private Initializer dataInit = new Initializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context = this;

        currentOrder = new Order();
        NavigationFragment navigationFragment = new NavigationFragment();
        Utils.navFragment = navigationFragment;
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_nav, navigationFragment);


    }

    @Override
    protected void onResume(){
        super.onResume();
        handleForwardNavigation(this, new NewOrderFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.new_order) {
            changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new NewOrderFragment());
            currentOrder = new Order();

        } else if (id == R.id.settings) {
            changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new SettingsFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**** On Click Functions ****/
    public void pickUp_onClick (View view){
        currentOrder.setDelivery(false);
        CustomerDetailsFragment fragment = new CustomerDetailsFragment();
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, fragment);
        Utils.currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
        navFragment.getView().findViewById(R.id.back_button).setVisibility(View.VISIBLE);
    }

    public void delivery_onClick (View view){
        currentOrder.setDelivery(true);
        CustomerDetailsFragment fragment = new CustomerDetailsFragment();
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, fragment);
        Utils.currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
        navFragment.getView().findViewById(R.id.back_button).setVisibility(View.VISIBLE);
    }

    public void next_onClick (View view){
        handleForwardNavigation(this, currFragment);
    }

    public void back_onClick (View view){
        handleBackwardNavigation(this, currFragment);
    }

    public void breakfast_onClick (View view) {
        Fragment fragment = MenuItemsFragment.newInstance(dataInit.getBreakfastItems());
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
    }

    public void lunch_onClick (View view) {
        Fragment fragment = MenuItemsFragment.newInstance(dataInit.getLunchItems());
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
    }

    public void sides_onClick (View view) {
        Fragment fragment = MenuItemsFragment.newInstance(dataInit.getSidesItems());
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
    }

    public void drinks_onClick (View view) {
        Fragment fragment = MenuItemsFragment.newInstance(dataInit.getDrinksItems());
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
    }

    public void dessert_onClick (View view) {
        Fragment fragment = MenuItemsFragment.newInstance(dataInit.getDessertItems());
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main,  fragment);
        currFragment = fragment;
        navFragment.getView().findViewById(R.id.next_button).setVisibility(View.VISIBLE);
    }

        @Override
    public void onListFragmentInteraction(com.example.raydel.substantialsubs.model.MenuItem item) {

            // get qty_dialog.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View qtyDialogView = li.inflate(R.layout.qty_dialog, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set qty_dialog.xml to alertdialog builder
            alertDialogBuilder.setView(qtyDialogView);

            TextView questionTextView = (TextView) qtyDialogView.findViewById(R.id.textView1);
            questionTextView.setText("How many " + item.getName() + " would you like?");

            TextView qtyTextView = (TextView) qtyDialogView.findViewById(R.id.QtytextView);
            qtyTextView.setText(Integer.toString(item.getQuantity()));

            qtyDialogView.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int qty = Integer.parseInt(qtyTextView.getText().toString());
                    qty++;
                    qtyTextView.setText(Integer.toString(qty));
                    item.setQuantity(qty);
                }
            });

            qtyDialogView.findViewById(R.id.minusButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int qty = Integer.parseInt(qtyTextView.getText().toString());

                    if(qty > 0) qty--;
                    qtyTextView.setText(Integer.toString(qty));
                    item.setQuantity(qty);
                }
            });


            // set dialog message
            alertDialogBuilder
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    changeFragment(getFragmentManager().beginTransaction(),
                                            R.id.fragment_main, MenuItemsFragment.newInstance(MenuItemsFragment.menuItems));
                                    Toast.makeText(MainActivity.this, item.getQuantity() + " " + item.getName() + " added to cart..", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this, "Selection cancelled", Toast.LENGTH_SHORT).show();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
}
