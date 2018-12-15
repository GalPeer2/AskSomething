package com.peer.gal.asksomething;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        theStateMgr = new StateMgr(this);
        asklSomeThingState = theStateMgr.LoadState();
        nameet = (EditText) findViewById(R.id.nameET);
        passwordet = (EditText) findViewById(R.id.passwordET);
        error = (TextView) findViewById(R.id.errorET);
    }

    public void logIn(View view)
    {
        if (asklSomeThingState==null) {
            error.setText("error,try again or register");return;
        }
        String name=nameet.getText().toString();
        String password=passwordet.getText().toString();
        if (asklSomeThingState.getDictionary().containsKey(name))
        {
            if (asklSomeThingState.getDictionary().get(name).getPassword().equals(password))
            {
                asklSomeThingState.setUserName(name);
                startActivity(new Intent(Login.this,MainActivity.class));
            }
            error.setText("error,try again or register");
        }
    }



}
