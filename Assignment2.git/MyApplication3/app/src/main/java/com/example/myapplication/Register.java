package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    ImageView imageView;
    String user;
    String pwd;
    EditText dateOfBirth,gmailText,passwrdText,nameText,genderText;
    DatePickerDialog picker;
    TextView signUpDifferentAccount;
    Button continueButton;
    LinearLayout linearLayout;
    boolean bs;
    DatabaseHelper db;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        linearLayout=findViewById(R.id.linearLayout);
        db = new DatabaseHelper(this);


        gmailText=findViewById(R.id.email_textview);
        passwrdText=findViewById(R.id.password_textview);
        nameText=findViewById(R.id.name_text);
        genderText=findViewById(R.id.gender_text);

        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(genderText.getText().toString());
        bs = ms.matches();



    imageView=findViewById(R.id.backView);
    dateOfBirth=findViewById(R.id.edit_dateofbirth);
    signUpDifferentAccount=findViewById(R.id.signUpWithDifferent);
    continueButton=findViewById(R.id.continue_button);

    //Signup with different account
    signUpDifferentAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    });


    //Image View for Back pressed
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    });


    //Calender for date of birth
    dateOfBirth.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(Register.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            dateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
            return true;
        }
    });

    //Continue if registered
        continueButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        user = gmailText.getText().toString().trim();
        pwd = passwrdText.getText().toString().trim();

        if( isValidates()) {


        }
        else {
            if (!user.matches(emailPattern)) {
                Snackbar sc= Snackbar.make(linearLayout,"Invalid Email Address ",Snackbar.LENGTH_LONG);
                sc.show();

            } else {

                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Snackbar sc= Snackbar.make(linearLayout,"You have Registered",Snackbar.LENGTH_LONG);
                        sc.show();

                        Intent moveToLogin = new Intent(Register.this, MainActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Snackbar sc= Snackbar.make(linearLayout,"Registration Error",Snackbar.LENGTH_LONG);
                        sc.show();

                    }


            }
        }




    }
});

    }
//
//    public void showdirect(View view)
//    {
//
//
//       // isValidates();
//        if(isValidates()) {
//            Intent intents = new Intent(getApplicationContext(), Otp_Activity.class);
//            startActivity(intents);
//        }


    private boolean isValidates()
    {
        if (nameText.getText().toString().trim().isEmpty())
        {
            Snackbar sc= Snackbar.make(linearLayout,"Enter the Name",Snackbar.LENGTH_LONG);
            sc.show();
            return true;

        }
        else if (genderText.getText().toString().trim().isEmpty())
        {
            Snackbar sc= Snackbar.make(linearLayout,"Enter Your gender",Snackbar.LENGTH_LONG);
            sc.show();
            return true;
        }
        else if (dateOfBirth.getText().toString().trim().isEmpty())
        {
            Snackbar sc= Snackbar.make(linearLayout,"Enter Your Date of Birth",Snackbar.LENGTH_LONG);
            sc.show();
            return true;
        }


        else if (user.isEmpty())
        {
            Snackbar sc= Snackbar.make(linearLayout,"Enter Your Email",Snackbar.LENGTH_LONG);
            sc.show();
            return true;
        }
        else if (pwd.isEmpty())
        {
            Snackbar sc= Snackbar.make(linearLayout,"Enter your Password",Snackbar.LENGTH_LONG);
            sc.show();
            return true;
        }

        return false;
    }
}

