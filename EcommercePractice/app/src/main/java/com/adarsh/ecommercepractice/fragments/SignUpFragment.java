package com.adarsh.ecommercepractice.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.adarsh.ecommercepractice.MainActivity;
import com.adarsh.ecommercepractice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    public SignUpFragment() {
        // Required empty public constructor
    }

    TextView AlreadyHaveAcc;
    FrameLayout frameLayout;
    EditText email, password, name, cPassword;
    Button signUp;
    ImageView closeBut;
    ProgressBar progressBar;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        AlreadyHaveAcc = view.findViewById(R.id.sign_up_signIn);
        frameLayout = getActivity().findViewById(R.id.sign_main_frame);

        email = view.findViewById(R.id.sign_up_email);
        password = view.findViewById(R.id.sign_up_pass);
        name = view.findViewById(R.id.sign_up_name);
        cPassword = view.findViewById(R.id.sign_up_confirm);

        signUp = view.findViewById(R.id.sign_up);
        closeBut = view.findViewById(R.id.Sign_up_close);

        progressBar = view.findViewById(R.id.sign_up_pb);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlreadyHaveAcc.setOnClickListener(this);

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
        name.addTextChangedListener(new TextWatcher() {
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
        password.addTextChangedListener(new TextWatcher() {
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
        cPassword.addTextChangedListener(new TextWatcher() {
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

        signUp.setOnClickListener(this);
        closeBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up_signIn:
                setFragment(new SignInFragment());
                break;
            case R.id.sign_up:
                    checkEmailandPassword();
                break;
            case R.id.Sign_up_close:
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
                getActivity().finish();
            default: break;
        }
    }

    private void checkEmailandPassword() {
        if(email.getText().toString().matches(emailPattern)){
            if(password.getText().toString().equals(cPassword.getText().toString())){

                //Internet works so Progress bar is set to Visible
                progressBar.setVisibility(View.VISIBLE);

                //It might be the case that user presses the signUP button again and again, so
                // data may be send again and again.To stop that Disabling the button
                signUp.setEnabled(false);
                signUp.setTextColor(Color.argb(50,255,255,255));

                mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Map<Object, String> userdata = new HashMap<>();
                            userdata.put("fullname", name.getText().toString());

                            firestore.collection("USERS").add(userdata)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if(task.isSuccessful()){

                                                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(mainIntent);
                                                getActivity().finish();
                                            }else{
                                                //If registration not successful. Then again we have to enable the
                                                // button
                                                signUp.setEnabled(true);
                                                signUp.setTextColor(Color.WHITE);
                                                //Making progress bar invisible again.
                                                progressBar.setVisibility(View.INVISIBLE);
                                                String error = task.getException().getMessage();
                                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            //If registration not successful. Then again we have to enable the
                            // button
                            signUp.setEnabled(true);
                            signUp.setTextColor(Color.WHITE);
                            //Making progress bar invisible again.
                            progressBar.setVisibility(View.INVISIBLE);
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                cPassword.setError("Password Doesn't matched! ");
            }
        }else{
                email.setError("Invalid Email!");
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        ft.replace(frameLayout.getId(), fragment).commit();
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(name.getText()))
            {
                if(!TextUtils.isEmpty(password.getText()) && password.length() >=8 )
                {
                    if(!TextUtils.isEmpty(cPassword.getText()))
                    {
                        signUp.setEnabled(true);
                        signUp.setTextColor(Color.YELLOW);
                    }else
                    {
                        signUp.setEnabled(false);
                        signUp.setTextColor(Color.WHITE);
                    }
                }else{
                    signUp.setEnabled(false);
                    signUp.setTextColor(Color.WHITE);
                }
            }else{
                signUp.setEnabled(false);
                signUp.setTextColor(Color.WHITE);
            }
        }else{
            signUp.setEnabled(false);
            signUp.setTextColor(Color.WHITE);
        }
    }
}
