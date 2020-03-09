package com.example.ecoversex.GeneralActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoversex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText forgot_password_email_input;
    Button forgot_password_email_confirm_btn,forgot_password_email_cancel_btn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();

        forgot_password_email_confirm_btn = (Button) findViewById(R.id.forgot_password_email_confirm_btn);
        forgot_password_email_input = (EditText) findViewById(R.id.forgot_password_email_input);
        forgot_password_email_cancel_btn = (Button) findViewById(R.id.forgot_password_email_cancel_btn);

        forgot_password_email_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
            }
        });

        forgot_password_email_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(forgot_password_email_input.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "Password sent to Email.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
