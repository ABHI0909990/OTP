package com.example.otp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Otp_verification2 extends AppCompatActivity {

    EditText InputNumber1,InputNumber2,InputNumber3,InputNumber4;
    String getOtpBtn, getMobileN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification2);

        final Button VerifyBtn = findViewById(R.id.verify_button);

        InputNumber1 = findViewById(R.id.otp_box_1);
        InputNumber2 = findViewById(R.id.otp_box_2);
        InputNumber3 = findViewById(R.id.otp_box_3);
        InputNumber4 = findViewById(R.id.otp_box_4);

        final ProgressBar progressBar = findViewById(R.id.prpgress_sending);

        TextView textView = findViewById(R.id.tx_mobile_number);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        getMobileN = getIntent().getStringExtra("mobile");
        getOtpBtn = getIntent().getStringExtra("backend");
        VerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!InputNumber1.getText().toString().trim().isEmpty() &&
                        !InputNumber1.getText().toString().trim().isEmpty() &&
                        !InputNumber1.getText().toString().trim().isEmpty() &&
                        !InputNumber1.getText().toString().trim().isEmpty() ){

                    String EnterOtp =
                            InputNumber1.getText().toString() +
                                    InputNumber2.getText().toString() +
                                    InputNumber3.getText().toString() +
                                    InputNumber4.getText().toString();

                    if(getOtpBtn!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        VerifyBtn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOtpBtn,EnterOtp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        VerifyBtn.setVisibility(View.INVISIBLE);

                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), Dashboard3.class);
                                            intent.putExtra("mobile",getMobileN);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(Otp_verification2.this,"Plz Enter the correct OTP",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(Otp_verification2.this,"Please check Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Otp_verification2.this,"Otp Verify",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Otp_verification2.this,"Please enter all number",Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        NumbertoMove();
    }

    private void NumbertoMove() {

        InputNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    InputNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InputNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    InputNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InputNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()){
                    InputNumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}