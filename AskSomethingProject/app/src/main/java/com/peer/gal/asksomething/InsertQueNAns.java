package com.peer.gal.asksomething;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class InsertQueNAns extends AppCompatActivity {
        EditText ans1et, ans2et, ans3et, ans4et, queet;
        String ans1, ans2, ans3, ans4, que;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_insert_que_nans);
            ans1et = (EditText) findViewById(R.id.ans1et);
            ans4et = (EditText) findViewById(R.id.ans4et);
            ans3et = (EditText) findViewById(R.id.ans3et);
            ans2et = (EditText) findViewById(R.id.ans2et);
            queet = (EditText) findViewById(R.id.queet);


        }

        public void takeData(View view) {
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setMessage("send this question?");
            a.setPositiveButton("send", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ans1 = ans1et.getText().toString();
                    ans2 = ans2et.getText().toString();
                    ans3 = ans3et.getText().toString();
                    ans4 = ans4et.getText().toString();
                    que = queet.getText().toString();
                    Intent toSendMail = new Intent(InsertQueNAns.this, SendMail.class);
                    toSendMail.putExtra("ans1", ans1);
                    toSendMail.putExtra("ans2", ans2);
                    toSendMail.putExtra("ans3", ans3);
                    toSendMail.putExtra("ans4", ans4);
                    toSendMail.putExtra("que", que);
                    startActivity(toSendMail);


                }
            });
            a.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = a.create();
            alertDialog.show();
        }
    }
