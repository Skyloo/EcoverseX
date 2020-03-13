package com.example.ecoversex.GeneralActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoversex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    //Declaring variables
    private Button register_register_btn;
    private EditText register_email_input,register_phone_number_input,register_password_input;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

      // linking text box with the variable name
        register_register_btn = (Button) findViewById(R.id.register_register_btn);
        register_email_input = (EditText) findViewById(R.id.register_email_input);
        register_phone_number_input = (EditText) findViewById(R.id.register_phone_number_input);
        register_password_input = (EditText) findViewById(R.id.register_password_input);
        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        register_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }
    private void CreateAccount () {

        // obtaining text input
        String email = register_email_input.getText().toString().trim();
        String phone = register_phone_number_input.getText().toString().trim();
        String password = register_password_input.getText().toString().trim();

        // checking text box when clicked
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please key in your Email...", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please key in your Phone Number...", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please key in your Password...", Toast.LENGTH_LONG).show();
        }
        if (password.length() <= 6) {
            register_password_input.setError("Password must be more than 6 characters");
            return;
        }

        // register user in FIREBASE
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Sign Up Success! Please check email for verification.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUpActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait, we are checking the credentials.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        //loadingBar.dismiss(); */
    }
}
