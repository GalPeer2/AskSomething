package com.peer.gal.asksomething;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.peer.gal.asksomething.R;
import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ShowAddresses extends AppCompatActivity {

    User user;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    ListView listView;
    ArrayList <String> items;
    EditText mailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);

        theStateMgr = new StateMgr(this );
        asklSomeThingState= theStateMgr.LoadState();
        user=asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        listView =(ListView)findViewById(R.id.ListView);
        mailET=(EditText)findViewById(R.id.emailET) ;

        items =user.getMyEmailAddresses();
        if (items.size()==0)
            Toast.makeText(ShowAddresses.this,"Enter your first contant email",Toast.LENGTH_LONG).show();

         ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String chosen =items.get(position);

                AlertDialog.Builder a = new AlertDialog.Builder(ShowAddresses.this);// maybe cuase problems!!!!
                a.setMessage("Sure you want to remove "+ chosen+" from your contacts?");
                a.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.getMyEmailAddresses().remove(chosen);
                        theStateMgr.SaveState(asklSomeThingState);
                        Toast.makeText(ShowAddresses.this,chosen+" has been removed ",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
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

        });


    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public void add(View view)
    {
        String theMail=mailET.getText().toString();
        if (isValid(theMail)==false)
        {
            Toast.makeText(ShowAddresses.this,"not valid mail",Toast.LENGTH_SHORT).show();
            return;

        }
        if (user.getMyEmailAddresses().contains(theMail))
        {
            Toast.makeText(ShowAddresses.this,"already exists",Toast.LENGTH_SHORT).show();
            return;

        }
        user.getMyEmailAddresses().add(theMail);
        theStateMgr.SaveState(asklSomeThingState);
        Toast.makeText(ShowAddresses.this,"done",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());

    }
    public void toMainActivity(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}
