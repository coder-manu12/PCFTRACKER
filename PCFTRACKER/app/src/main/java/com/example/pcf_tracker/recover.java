package com.example.pcf_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class recover extends AppCompatActivity {

    RadioButton PN, EA;
    EditText inputField, otpField;
    Button sendOtpBtn, verifyBtn;

    String generatedOtp;
    long otpTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        PN = findViewById(R.id.PN);
        EA = findViewById(R.id.EA);
        inputField = findViewById(R.id.inputField);
        otpField = findViewById(R.id.otpField);
        sendOtpBtn = findViewById(R.id.sendOtpBtn);
        verifyBtn = findViewById(R.id.verifyBtn);

        sendOtpBtn.setOnClickListener(v -> sendOtp());
        verifyBtn.setOnClickListener(v -> verifyOtp());
    }

    // 🔹 Generate OTP
    private void sendOtp() {

        String userInput = inputField.getText().toString();

        generatedOtp = String.valueOf((int)(Math.random() * 900000) + 100000);
        otpTime = System.currentTimeMillis();

        if (EA.isChecked()) {
            // 📧 EMAIL OTP (demo using intent)
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{userInput});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Your OTP Code");
            intent.putExtra(Intent.EXTRA_TEXT, "Your OTP is: " + generatedOtp);

            try {
                startActivity(Intent.createChooser(intent, "Send Email"));
            } catch (Exception e) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }

        } else if (PN.isChecked()) {
            // 📱 PHONE OTP (placeholder);
            Toast.makeText(this, "SMS OTP would be sent via Firebase", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "OTP generated (valid 5 min)", Toast.LENGTH_SHORT).show();
    }

    // 🔹 Verify OTP
    private void verifyOtp() {

        String userOtp = otpField.getText().toString();

        long currentTime = System.currentTimeMillis();

        // ⏳ 5-minute expiry
        if (currentTime - otpTime > 300000) {
            Toast.makeText(this, "OTP Expired", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userOtp.equals(generatedOtp)) {

            Toast.makeText(this, "Verified ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,afterlogin.class);
            startActivity(intent);
            // then bring all inforamtion about the user (but how do i do that);
        } else {
            Toast.makeText(this, "Wrong OTP ", Toast.LENGTH_SHORT).show();
        }
    }
}