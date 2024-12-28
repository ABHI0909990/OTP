package com.example.otp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard3);

        TextView contactTextView = findViewById(R.id.contactTextView);
        String phoneNumber = getIntent().getStringExtra("mobile");
        contactTextView.setText(phoneNumber);
    }
}

