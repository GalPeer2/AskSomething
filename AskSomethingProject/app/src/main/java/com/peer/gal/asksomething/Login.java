package com.peer.gal.asksomething;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.peer.gal.asksomething.R;
import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;
import com.sun.mail.imap.protocol.ENVELOPE;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    EditText nameet, passwordet;
    Button enter,loginORregister;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Listen2MailResultsService.startAction(this , "" , "" );

        theStateMgr = new StateMgr(this);

        asklSomeThingState = theStateMgr.LoadState();

        nameet = (EditText) findViewById(R.id.nameET);
        passwordet = (EditText) findViewById(R.id.passwordET);
        enter=(Button) findViewById(R.id.buttonStart);
        enter.setBackgroundColor(Color.MAGENTA);
        loginORregister=(Button)findViewById(R.id.buttonRegister);
        title=(TextView)findViewById(R.id.theTitle);

        //example
       /* Question a =new Question("the que ?","a","b","c","d");
        ArrayList<String> s = new ArrayList<String>();
        s.add("str1");
        s.add("str hello");
        s.add("str bye");
        a.setVotersForAns1(new ArrayList<String>(s));
        asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName()).getMyHistoryQuestions().add(a);
        */

    // startActivity(new Intent(Login.this,SendMail.class));
    }

    public void logIn2(View view)
    {

        Intent toMainActivity=new Intent(Login.this,MainActivity.class);
        //log in
        if (enter.getText().equals("LOG IN")) {
            if (asklSomeThingState == null) {
                Toast.makeText(Login.this,"You should register first",Toast.LENGTH_SHORT).show();
                return;
            }
            String name = nameet.getText().toString();
            String password = passwordet.getText().toString();
            if (asklSomeThingState.getDictionary().containsKey(name)) {
                if (asklSomeThingState.getDictionary().get(name).getPassword().equals(password)) {
                    asklSomeThingState.setUserName(name);
                    theStateMgr.SaveState(asklSomeThingState);
                    startActivity(toMainActivity);
                }
                else
                Toast.makeText(Login.this,"Wrong password or user name",Toast.LENGTH_SHORT).show();
            }
            else
            Toast.makeText(Login.this,"Wrong password or user name",Toast.LENGTH_SHORT).show();
            return;
        }
        //first register
        if (asklSomeThingState==null) {
            asklSomeThingState = new AsklSomeThingState();
            User user = new User(nameet.getText().toString(), passwordet.getText().toString());
            asklSomeThingState.getDictionary().put(nameet.getText().toString(), user);
            asklSomeThingState.setUserName(nameet.getText().toString());
            theStateMgr.SaveState(asklSomeThingState);
            startActivity(toMainActivity);
            return;
        }
        // name allredy used
        if (asklSomeThingState.getDictionary().containsKey(nameet.getText().toString())) {
            Toast.makeText(Login.this,"user name is allready used!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        //no user name in dictinary
        User user = new User(nameet.getText().toString(), passwordet.getText().toString());
        asklSomeThingState.getDictionary().put(nameet.getText().toString(), user);
        asklSomeThingState.setUserName(nameet.getText().toString());
        theStateMgr.SaveState(asklSomeThingState);
        startActivity(toMainActivity);

    }


    public void changeToSignUp(View view)
    {
        if (loginORregister.getText().toString().equals("REGISTER")) {
            nameet.setText("enter a name for user name");
            passwordet.setText("enter a password");
            enter.setText("sign up");
            enter.setBackgroundColor(Color.RED);
            title.setText("please sign up:");
            loginORregister.setText("Log In");
            loginORregister.setBackgroundColor(Color.MAGENTA);
            return;
        }
        nameet.setText("USER NAME");
        passwordet.setText("PASSWORD");
        enter.setText("LOG IN");
        enter.setBackgroundColor(Color.MAGENTA);
        title.setText("please log in:");
        loginORregister.setText("REGISTER");
        loginORregister.setBackgroundColor(Color.RED);


    }



}
