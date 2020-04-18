package com.adarsh.ecommercepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.adarsh.ecommercepractice.fragments.SignInFragment;

public class LoginPage extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        frameLayout = (FrameLayout) findViewById(R.id.sign_main_frame);
        setFragment(new SignInFragment());
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment).commit();
    }
}
