package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

public class Otp extends AppCompatActivity {
    ImageView imageView;
    LinearLayout linearLayout;
    Button reSend, submitButton;
    PinEntryEditText pinEntry;
    EditText edtOne;
    EditText edtTwo;
    EditText edtThree;
    EditText edtFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        imageView = findViewById(R.id.backImage);
        reSend = findViewById(R.id.reSend);
        submitButton = findViewById(R.id.submitButton);
        linearLayout = findViewById(R.id.linearLayout);
       // pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        edtOne = findViewById(R.id.et_otp_one);
        edtTwo = findViewById(R.id.et_otp_two);
        edtThree = findViewById(R.id.et_otp_three);
        edtFour = findViewById(R.id.et_otp_four);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
        reSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar sc = Snackbar.make(linearLayout, "SMS Resent Successfully", Snackbar.LENGTH_LONG);
                sc.show();
            }
        });


//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (pinEntry != null) {
//                    pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
//
//                        @Override
//                        public void onPinEntered(CharSequence str) {
//                            if (str.toString().equals("1234")) {
//                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                                startActivity(i);
//                            }
//                        }
//                    });
//                }
//
//
//                else {
//                    Snackbar sc = Snackbar.make(linearLayout, "Please Enter the SMS", Snackbar.LENGTH_LONG);
//                    sc.show();
//
//                }
//
//            }
//        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidates();
            }
        });

    }

    public void isValidates()
    {
        if (edtOne.getText().toString().equals(" ") || edtTwo.getText().toString().equals(" ") || edtThree.getText().toString().equals("3 ") || edtFour.getText().toString().equals(""))
        {
            Snackbar sc = Snackbar.make(linearLayout, "Enter otp", Snackbar.LENGTH_LONG);
            sc.show();
        }
        else
        {



            Snackbar sc = Snackbar.make(linearLayout, "Successful", Snackbar.LENGTH_LONG);
            sc.show();
        }
    }

}

