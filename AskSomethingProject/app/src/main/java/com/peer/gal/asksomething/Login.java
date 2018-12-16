package com.peer.gal.asksomething;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peer.gal.asksomething.R;
import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

public class Login extends AppCompatActivity {

    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    TextView error;
    EditText nameet, passwordet;
    Button enter,loginORregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        theStateMgr = new StateMgr(this);
        asklSomeThingState =new AsklSomeThingState();
        asklSomeThingState = theStateMgr.LoadState();
        nameet = (EditText) findViewById(R.id.nameET);
        passwordet = (EditText) findViewById(R.id.passwordET);
        error = (TextView) findViewById(R.id.errorET);
        enter=(Button) findViewById(R.id.buttonStart);
        loginORregister=(Button)findViewById(R.id.buttonRegister);
    }

    public void logIn2(View view)
    {
        //log in
        if (enter.getText().equals("LOG IN")) {
            if (asklSomeThingState == null) {
                error.setText("error,try again or register");
                return;
            }
            String name = nameet.getText().toString();
            String password = passwordet.getText().toString();
            if (asklSomeThingState.getDictionary().containsKey(name)) {
                if (asklSomeThingState.getDictionary().get(name).getPassword().equals(password)) {
                    asklSomeThingState.setUserName(name);
                    theStateMgr.SaveState(asklSomeThingState);
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else
                    error.setText("error,try again or register");
            }
            error.setText("error,try again or register");
            return;
        }
        //first register
        if (asklSomeThingState==null) {
            asklSomeThingState = new AsklSomeThingState();
            User user = new User(nameet.getText().toString(), passwordet.getText().toString());
            asklSomeThingState.getDictionary().put(nameet.getText().toString(), user);
            asklSomeThingState.setUserName(nameet.getText().toString());
            theStateMgr.SaveState(asklSomeThingState);
            startActivity(new Intent(Login.this, MainActivity.class));
            return;
        }
        // name allredy used
        if (asklSomeThingState.getDictionary().containsKey(nameet.getText().toString())) {
            error.setText("user name is allready used ! choose anoter one");
            return;
        }
        //no user name in dictinary
        User user = new User(nameet.getText().toString(), passwordet.getText().toString());
        asklSomeThingState.getDictionary().put(nameet.getText().toString(), user);
        asklSomeThingState.setUserName(nameet.getText().toString());
        theStateMgr.SaveState(asklSomeThingState);
        startActivity(new Intent(Login.this, MainActivity.class));

    }


    public void changeToSignUp(View view)
    {
        if (loginORregister.getText().toString().equals("REGISTER")) {
            nameet.setText("enter a name for user name");
            passwordet.setText("enter a password");
            enter.setText("sign up");
            error.setText("");
            loginORregister.setText("Log In");
            return;
        }
        nameet.setText("USER NAME");
        passwordet.setText("PASSWORD");
        enter.setText("LOG IN");
        error.setText("");
        loginORregister.setText("REGISTER");


    }



}
