package com.example.ecoversex.GeneralActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.AdminActivity.AdminActivity;
import com.example.ecoversex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email_input, login_password_input, usertype_input;
    private Button login_login_btn;
    private FirebaseAuth firebaseAuth;
    TextView forget_password_link;
    CheckBox remember_me_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_input = (EditText) findViewById(R.id.login_email_input);
        login_password_input = (EditText) findViewById(R.id.login_password_input);
        login_login_btn = (Button) findViewById(R.id.login_login_btn);
        //usertype_input = (EditText) findViewById(R.id.usertype_input);
        forget_password_link = (TextView) findViewById(R.id.forget_password_link);
        remember_me_checkbox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        firebaseAuth = FirebaseAuth.getInstance();

        // forget password activity link
        forget_password_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Get from firebase user name, user email, user photo
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

        }
            login_login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // final String usertype = usertype_input.getText().toString().trim();
                    String email = login_email_input.getText().toString().trim();
                    String password = login_password_input.getText().toString().trim();

                    // checking text box when clicked

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(LoginActivity.this, "Please key in your Email...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginActivity.this, "Please key in your Password...", Toast.LENGTH_LONG).show();
                    }
                    if (password.length() <= 4) {
                        login_password_input.setError("Password must be more than 4 characters");
                        return;
                    }

                    // Authenticate User
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                        //UserType then go to different home page
                                        /*if (usertype == "ecoverseadmin") {
                                            Toast.makeText(LoginActivity.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this, "User Login Successful!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                                        }*/
                                        Toast.makeText(LoginActivity.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Please verify your email address.", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
            });

        }

    }
