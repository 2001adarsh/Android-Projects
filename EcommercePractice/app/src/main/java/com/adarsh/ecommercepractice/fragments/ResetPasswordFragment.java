package com.adarsh.ecommercepractice.fragments;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adarsh.ecommercepractice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener {

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    EditText email;
    Button reset;
    TextView goBack;
    FrameLayout frameLayout;
    FirebaseAuth mAuth;

    ViewGroup emailIconContainer;
    ImageView mail;
    TextView emailIconText;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        email = view.findViewById(R.id.reset_email);
        reset = view.findViewById(R.id.reset);
        goBack = view.findViewById(R.id.reset_goback);
        frameLayout = getActivity().findViewById(R.id.sign_main_frame);
        mAuth = FirebaseAuth.getInstance();

         emailIconContainer = view.findViewById(R.id.status_container);
         mail = view.findViewById(R.id.mail);
         emailIconText = view.findViewById(R.id.def_text);
         progressBar = view.findViewById(R.id.reset_pb);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        goBack.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText().toString())){
            reset.setEnabled(true);
            reset.setTextColor(Color.WHITE);
        }else{
            reset.setEnabled(false);
            reset.setTextColor(Color.LTGRAY);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.reset_goback:
                setFragment(new SignInFragment());
                break;
            case R.id.reset:

                //showing icon and progress bar
                TransitionManager.beginDelayedTransition(emailIconContainer);
                mail.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                reset.setEnabled(false);
                reset.setTextColor(Color.LTGRAY);
                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            emailIconText.setVisibility(View.VISIBLE);

                        }else{
                            String error = task.getException().getMessage();
                            emailIconText.setText(error);
                            emailIconText.setTextColor(Color.RED);

                            TransitionManager.beginDelayedTransition(emailIconContainer);
                            emailIconText.setVisibility(View.VISIBLE);
                            reset.setEnabled(true);
                            reset.setTextColor(Color.WHITE);
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });

                break;
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        ft.replace(frameLayout.getId(), fragment).commit();
    }
}
