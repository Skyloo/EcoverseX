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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.AdminActivity.AdminActivity;
import com.example.ecoversex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText admin_login_email_input, admin_login_password_input;
    private Button admin_login_login_btn;
    private FirebaseAuth firebaseAuth;
    TextView admin_forget_password_link;
    CheckBox admin_remember_me_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_login_email_input = (EditText) findViewById(R.id.admin_login_email_input);
        admin_login_password_input = (EditText) findViewById(R.id.admin_login_password_input);
        admin_login_login_btn = (Button) findViewById(R.id.admin_login_login_btn);
        admin_forget_password_link = (TextView) findViewById(R.id.admin_forget_password_link);
        admin_remember_me_checkbox = (CheckBox) findViewById(R.id.admin_remember_me_checkbox);
        firebaseAuth = FirebaseAuth.getInstance();

        // forget password activity link (same link)
        admin_forget_password_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Get from firebase user name, user email, user photo
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

        }

        admin_login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = admin_login_email_input.getText().toString().trim();
                String password = admin_login_password_input.getText().toString().trim();

                // checking text box when clicked

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(AdminLoginActivity.this, "Please key in your Email...", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(AdminLoginActivity.this, "Please key in your Password...", Toast.LENGTH_LONG).show();
                }
                if (password.length() <= 4) {
                    admin_login_password_input.setError("Password must be more than 4 characters");
                    return;
                }

                // Authenticate User
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(AdminLoginActivity.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                            } else {
                                Toast.makeText(AdminLoginActivity.this, "Please verify your email address.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(AdminLoginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
