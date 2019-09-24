package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView register;
ImageView imageView;
EditText password,gmailText;
Button loginButton;
boolean flag=true;
LinearLayout linearLayout;
DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=findViewById(R.id.register_text_view);
        imageView=findViewById(R.id.image_Visible);
        password=findViewById(R.id.password);
        gmailText=findViewById(R.id.gmail_edit_text);
        linearLayout=findViewById(R.id.linearLayout);
        loginButton=findViewById(R.id.login_button);

        db = new DatabaseHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag)
                {
                   // imageView.setImageResource(R.id.);

                    imageView.setImageResource(R.drawable.ic_visibility_black_24dp);
                    flag=false;
                   // password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else{
                    imageView.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    flag=true;
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
             // password.setInputType();

                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = gmailText.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                    Intent HomePage = new Intent(MainActivity.this,Otp.class);
                    startActivity(HomePage);
                }
                else
                {
                    Snackbar sc= Snackbar.make(linearLayout,"Login Error",Snackbar.LENGTH_LONG);
                    sc.show();
                 //   Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
//    public void logIn(View v)
//    {
//        Intent i=new Intent(getApplicationContext(),Otp.class);
//        startActivity(i);
//    }
}
