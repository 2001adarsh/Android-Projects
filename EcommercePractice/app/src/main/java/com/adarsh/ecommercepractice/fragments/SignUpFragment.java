package com.adarsh.ecommercepractice.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.adarsh.ecommercepractice.R;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    public SignUpFragment() {
        // Required empty public constructor
    }

    TextView AlreadyHaveAcc;
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        AlreadyHaveAcc = view.findViewById(R.id.sign_up_signIn);
        frameLayout = getActivity().findViewById(R.id.sign_main_frame);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlreadyHaveAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up_signIn:
                setFragment(new SignInFragment());
                break;
            default: break;
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment).commit();
    }
}
