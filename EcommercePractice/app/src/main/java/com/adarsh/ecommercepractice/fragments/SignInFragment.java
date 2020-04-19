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
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class SignInFragment extends Fragment implements View.OnClickListener {
    public SignInFragment() {
        // Required empty public constructor
    }

    TextView DontHaveAcc;
    FrameLayout frameLayout;
    EditText email, password;
    Button signIn;
    ImageView closeBut;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_sign_in, container, false);

        DontHaveAcc = (TextView) view.findViewById(R.id.sign_in_signup);
        frameLayout = getActivity().findViewById(R.id.sign_main_frame);
        email = (EditText) view.findViewById(R.id.sign_in_email);
        password = (EditText) view.findViewById(R.id.sign_in_passwrod);
        signIn = (Button) view.findViewById(R.id.sign_in);
        closeBut = (ImageView) view.findViewById(R.id.Sign_in_close);

        progressBar = view.findViewById(R.id.sign_in_pb);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DontHaveAcc.setOnClickListener(this);
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
        signIn.setOnClickListener(this);
        closeBut.setOnClickListener(this);

    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText().toString())){
            if(!TextUtils.isEmpty(password.getText().toString())){
                signIn.setEnabled(true);
                signIn.setTextColor(Color.YELLOW);
            }else{
                signIn.setEnabled(false);
                signIn.setTextColor(Color.LTGRAY);
            }
        }else{
            signIn.setEnabled(false);
            signIn.setTextColor(Color.LTGRAY);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.sign_in_signup:
                setFragment(new SignUpFragment());
                break;
            case R.id.sign_in:
                checkEmailandPassword();
                break;
            case R.id.Sign_in_close:
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
                getActivity().finish();
            default: break;
        }
    }

    private void checkEmailandPassword(){
        if(email.getText().toString().matches(emailPattern)){
            if(password.length()>=8){

                progressBar.setVisibility(View.VISIBLE);
                signIn.setEnabled(false);
                signIn.setTextColor(Color.LTGRAY);

                //signIn
                mAuth.signInWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainIntent);
                            getActivity().finish();
                        }else{
                            progressBar.setVisibility(View.INVISIBLE);
                            signIn.setEnabled(true);
                            signIn.setTextColor(Color.YELLOW);
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }else{
                Toast.makeText(getActivity(), "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        ft.replace(frameLayout.getId(), fragment).commit();
    }
}
