package com.example.raydel.substantialsubs;

import android.app.AlertDialog;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raydel.substantialsubs.fragments.CustomerDetailsFragment;
import com.example.raydel.substantialsubs.fragments.MenuFragment;
import com.example.raydel.substantialsubs.fragments.MenuItemsFragment;
import com.example.raydel.substantialsubs.fragments.NewOrderFragment;
import com.example.raydel.substantialsubs.fragments.SettingsFragment;
import com.example.raydel.substantialsubs.model.Order;
import com.example.raydel.substantialsubs.utils.Initializer;

import static com.example.raydel.substantialsubs.utils.Utils.changeFragment;
import static com.example.raydel.substantialsubs.utils.Utils.currentOrder;
import static com.example.raydel.substantialsubs.utils.Utils.getInputText;
import static com.example.raydel.substantialsubs.utils.Utils.validateAddress;


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
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new NewOrderFragment());
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
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new CustomerDetailsFragment());

    }

    public void delivery_onClick (View view){
        currentOrder.setDelivery(true);
        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new CustomerDetailsFragment());
    }

    public void custDetailsNext_onClick (View view){

        currentOrder.setName(getInputText(this, R.id.name_editText));
        currentOrder.setPhone(getInputText(this, R.id.phone_editText));

        if(currentOrder.isDelivery()) {
            currentOrder.setAddress(getInputText(this, R.id.address_editText));
            currentOrder.setCcNumber(getInputText(this, R.id.ccNumber_editText));
            currentOrder.setCcExpMM(getInputText(this, R.id.ccExpMM_editText));
            currentOrder.setCcExpYYYY(getInputText(this, R.id.ccExpYYYY_editText));
            currentOrder.setCcCVV(getInputText(this, R.id.ccCVV_editText));


            RadioGroup paymentMethod = (RadioGroup) this.findViewById(R.id.paymentMethod_radioGroup);

            switch (paymentMethod.getCheckedRadioButtonId()) {
                case R.id.cash_radioButton:
                    currentOrder.setCard(false);
                    break;
                case R.id.creditCard_radioButton:
                    currentOrder.setCard(true);
                    break;
                default:
                    Toast.makeText(this, "Invalid Payment Method", Toast.LENGTH_SHORT).show();
                    return;
            }

            validateAddress(this);
        }else {
            changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new MenuFragment());
        }
    }

    public void custDetailsBack_onClick (View view){

        currentOrder.setName(getInputText(this, R.id.name_editText));
        currentOrder.setPhone(getInputText(this, R.id.phone_editText));
        currentOrder.setAddress(getInputText(this, R.id.address_editText));
        currentOrder.setCcNumber(getInputText(this, R.id.ccNumber_editText));
        currentOrder.setCcExpMM(getInputText(this, R.id.ccExpMM_editText));
        currentOrder.setCcExpYYYY(getInputText(this, R.id.ccExpYYYY_editText));
        currentOrder.setCcCVV(getInputText(this, R.id.ccCVV_editText));


        RadioGroup paymentMethod = (RadioGroup) this.findViewById(R.id.paymentMethod_radioGroup);

        switch (paymentMethod.getCheckedRadioButtonId()) {
            case R.id.cash_radioButton:
                currentOrder.setCard(false);
                break;
            case R.id.creditCard_radioButton:
                currentOrder.setCard(true);
                break;
            default:
        }

        changeFragment(getFragmentManager().beginTransaction(),R.id.fragment_main, new NewOrderFragment());
    }

    public void breakfast_onClick (View view) {
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main, MenuItemsFragment.newInstance(dataInit.getBreakfastItems()));
    }

    public void lunch_onClick (View view) {
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main, MenuItemsFragment.newInstance(dataInit.getLunchItems()));
    }

    public void sides_onClick (View view) {
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main, MenuItemsFragment.newInstance(dataInit.getSidesItems()));
    }

    public void drinks_onClick (View view) {
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main, MenuItemsFragment.newInstance(dataInit.getDrinksItems()));
    }

    public void dessert_onClick (View view) {
        changeFragment(getFragmentManager().beginTransaction(),
                R.id.fragment_main, MenuItemsFragment.newInstance(dataInit.getDessertItems()));
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
