package com.example.pcf_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

 public void doLogin(View view){
     EditText user= findViewById(R.id.username);
     EditText pass = findViewById(R.id.Password);
       String u = user.getText().toString();
       String p = pass.getText().toString();
       int x=1;
        if (x==1){
            Toast.makeText(MainActivity.this, "welcome " + u,Toast.LENGTH_SHORT ).show();
            //Toast.makeText(afterlogin.class, "welcome " + "u",Toast.LENGTH_LONG ).show();
            Intent intent = new Intent(MainActivity.this, afterlogin.class);
            startActivity(intent);
            // then i need to bring all information related  to that user ;
            // for git

        }
        else{
            Toast.makeText(MainActivity.this,"incorrect username or password", Toast.LENGTH_SHORT).show();
        }
 }
public void doSighin(View view){
  Intent intent = new Intent(MainActivity.this, signingin.class);
  startActivity(intent);
}
public void doRecover(View view){
Intent intent = new Intent(MainActivity.this,recover.class);
startActivity(intent);
}
}
