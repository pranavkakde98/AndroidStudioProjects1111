package com.example.hp.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.afinal.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
ActivityLoginBinding binding;

    private Button button_login;
    private EditText et_email_address;
    private EditText et_password;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
        }

        progressDialog = new ProgressDialog(this);


        et_email_address = (EditText) findViewById(R.id.et_email_address);
        et_password = (EditText) findViewById(R.id.et_password);
        button_login = (Button) findViewById(R.id.button_login);

        button_login.setOnClickListener(this);



    }

    public void signup(View view){
        startActivity(new Intent(this,SignupActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == button_login){
            userLogin();
        }
    }

    private void userLogin() {
        String email = et_email_address.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show();
            return;
        }


        //if validations ok
        //Show progress bar

        progressDialog.setMessage("Logging you in");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),GoToMap.class));
                        }
                    }
                });
    }
}
