package com.adarsh.basicbottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.adarsh.basicbottomnav.ui.FavoriteFragment;
import com.adarsh.basicbottomnav.ui.HomeFragment;
import com.adarsh.basicbottomnav.ui.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.main_frame);
        bottomNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment= new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        settingsFragment = new SettingsFragment();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bot_home:
                        bottomNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_set:
                        bottomNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(settingsFragment);
                        return true;

                    case R.id.nav_fav:
                        bottomNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(favoriteFragment);
                        return true;

                    default:
                    return false;
                }
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }

}
