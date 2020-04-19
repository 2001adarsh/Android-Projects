package com.adarsh.ecommercepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.adarsh.ecommercepractice.fragments.SignInFragment;

public class LoginPage extends AppCompatActivity {

    public static Boolean OnResetPasswordFragment = false;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        frameLayout = (FrameLayout) findViewById(R.id.sign_main_frame);
        setDefaultFragment(new SignInFragment());
    }
    private void setDefaultFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment).commit();
    }

    //On back Button pressed of Android phone

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(OnResetPasswordFragment == true){
                OnResetPasswordFragment=false;
                setFragment(new SignInFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        ft.replace(frameLayout.getId(), fragment).commit();
    }
}
