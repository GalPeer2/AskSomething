package com.peer.gal.asksomething;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Toolbar mToolbar = (Toolbar) findViewById(R.menu.main_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

    }

    public void exit(View view) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setMessage("Sure you want to exit?");
        a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent a1,a2,a3;
        if (item.getItemId()==R.id.toAdresses) {
      //     a1 =new Intent(MainActivity.this,ShowAddresses.class);
        //    startActivity(a1);
        }
        if (item.getItemId()==R.id.infoapp) {
       //     a2 =new Intent(MainActivity.this,InfoApp.class);
      //      startActivity(a2);
        }
        if (item.getItemId()==R.id.exit) {
            exit(null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }
    public void moveToStartWorking(View view)
    {
       // startActivity(new Intent(MainActivity.this,StartWorking.class));
    }

}
