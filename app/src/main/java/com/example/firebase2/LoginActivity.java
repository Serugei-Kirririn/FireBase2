package com.example.firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edLogin,edPass;
    private FirebaseAuth mAuth;
    private Button bStart,bSignUp,bSignIn,signOut;
    private TextView tvUserName;
    @Override
    protected  void  onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }
    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null)
        {
        showSigned();
            String userName = "вы вошли как : " + cUser.getEmail();
            tvUserName.setText(userName);

            Toast.makeText(this,"User not null",Toast.LENGTH_SHORT).show();
        }
        else{

          notSigned();


            Toast.makeText(this,"User null",Toast.LENGTH_SHORT).show();
        }
    }
    private void init(){
       signOut = findViewById(R.id.bSignOut);
        tvUserName = findViewById(R.id.tvUserEmail);
        bStart = findViewById(R.id.bStart);
        bSignIn = findViewById(R.id.bSignIn);
        bSignUp = findViewById(R.id.bSignUp);
        edLogin = findViewById(R.id.edLogin);
        edPass = findViewById(R.id.edPass);
        mAuth = FirebaseAuth.getInstance();
    }
    public  void onClickSignUp(View view){
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPass.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),(edPass.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
     showSigned();
     sendEmailVer();
                        Toast.makeText(getApplicationContext(), "User SignUp",Toast.LENGTH_SHORT).show();

                    }
                    else {
notSigned();
                        Toast.makeText(getApplicationContext(), "User SignUp failed",Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter email and pass",Toast.LENGTH_SHORT).show();
        }
    }
    public  void onClickSignIn(View view){

        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPass.getText().toString())){
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(),edPass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showSigned();
                        Toast.makeText(getApplicationContext(), "User SignIn Successful",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        notSigned();
                        Toast.makeText(getApplicationContext(), "User SignIn failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void onClickSignOut(View view){
        FirebaseAuth.getInstance().signOut();
notSigned();

    }
    private  void showSigned() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            String userName = "вы вошли как : " + user.getEmail();
            tvUserName.setText(userName);
            bStart.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.VISIBLE);
            edLogin.setVisibility(View.GONE);
            edPass.setVisibility(View.GONE);
            bSignIn.setVisibility(View.GONE);
            bSignUp.setVisibility(View.GONE);
        }
        else {
            Toast.makeText(getApplicationContext(), "Проверте вашу почту для подтверждения",Toast.LENGTH_SHORT).show();
        }
    }
    private  void notSigned(){
        bStart.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        signOut.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPass.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
        bSignUp.setVisibility(View.VISIBLE);
    }
    public void onClickStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public  void sendEmailVer(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(getApplicationContext(), "Проверте вашу почту для подтверждения",Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(getApplicationContext(), "Send Email failed",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}

