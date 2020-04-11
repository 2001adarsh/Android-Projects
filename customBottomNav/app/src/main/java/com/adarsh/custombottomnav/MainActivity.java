package com.adarsh.custombottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.adarsh.custombottomnav.ui.CartFragment;
import com.adarsh.custombottomnav.ui.HomeFragment;
import com.adarsh.custombottomnav.ui.ProfileFragment;
import com.adarsh.custombottomnav.ui.SearchFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity  implements ChipNavigationBar.OnItemSelectedListener {
    private static final String TAG = "FragmentT";
    ChipNavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBar = findViewById(R.id.main_nav);

        if(savedInstanceState==null)
        {
            navigationBar.setItemSelected(R.id.nav_home,true);
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();

        }
        navigationBar.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment = null;
        switch (i){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_cart:
                fragment = new CartFragment();
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
        }

        if(fragment!= null)
        {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_frame, fragment).commit();
        }
        else{
            Log.e(TAG, "Error in creating fragment");
        }
    }
}
