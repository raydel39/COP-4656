package com.example.raydel.substantialsubs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.raydel.substantialsubs.fragments.CustomerDetailsFragment;
import com.example.raydel.substantialsubs.fragments.MenuFragment;
import com.example.raydel.substantialsubs.fragments.NewOrderFragment;
import com.example.raydel.substantialsubs.fragments.SettingsFragment;
import com.example.raydel.substantialsubs.model.Order;

import static com.example.raydel.substantialsubs.utils.Utils.changeFragment;
import static com.example.raydel.substantialsubs.utils.Utils.currentOrder;
import static com.example.raydel.substantialsubs.utils.Utils.getInputText;
import static com.example.raydel.substantialsubs.utils.Utils.validateAddress;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Context context;

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

}
