package com.example.otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText EnterNumber;
    private Button sendOtpButton;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterNumber = findViewById(R.id.phoneEditText);
        sendOtpButton = findViewById(R.id.sendOtpButton);
        ProgressBar progressBar = findViewById(R.id.prpgress_sending);


        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!EnterNumber.getText().toString().trim().isEmpty()){
                    if((EnterNumber.getText().toString().trim().length()) == 10){

                        progressBar.setVisibility(View.VISIBLE);
                        sendOtpButton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + EnterNumber.getText().toString(), 60, TimeUnit.SECONDS, MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        sendOtpButton.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        sendOtpButton.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        sendOtpButton.setVisibility(View.VISIBLE);

                                        Intent i = new Intent(getApplicationContext(),Otp_verification2.class);
                                        i.putExtra("mobile",EnterNumber.getText().toString());
                                        i.putExtra("backend",backendotp);

                                        startActivity(i);
                                    }
                                }
                        );
//                        Intent i = new Intent(getApplicationContext(),Otp_verification2.class);
//                        i.putExtra("mobile",EnterNumber.getText().toString());
//                        startActivity(i);
                    }else {
                        Toast.makeText(MainActivity.this,"Please enter a correct a number",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Enter a Mobile Number",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


}