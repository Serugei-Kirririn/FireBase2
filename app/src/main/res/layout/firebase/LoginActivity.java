package com.example.firebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
       if(cUser != null){
           Toast.makeText(this,"User not null",Toast.LENGTH_SHORT).show();
       }
       else{
           Toast.makeText(this,"User null",Toast.LENGTH_SHORT).show();
       }
   }
   private void init(){
       edLogin = findViewById(R.id.edLogin);
       edPass = findViewById(R.id.edPass);
      mAuth = FirebaseAuth.getInstance();
   }
   public  void onClickSignUp(View view){
if(!TextUtils.isEmpty(edLogin.getText().toString())&& !TextUtils.isEmpty(edPass.getText().toString()))
{
    mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),(edPass.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){

Toast.makeText(getApplicationContext(), "User SignUp",Toast.LENGTH_SHORT).show();

}
else {

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

   }
}

