package com.peer.gal.asksomething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.util.ArrayList;

public class ShowVoters extends AppCompatActivity {
    User user;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    int thePlace=0;
    int answerIndex=1;
    TextView queTV,ansTV;
    ListView votersLV;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_voters);

        theStateMgr = new StateMgr(this);
        asklSomeThingState = theStateMgr.LoadState();
        user = asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        queTV=(TextView)findViewById(R.id.queTV);
        ansTV=(TextView)findViewById(R.id.ansTV);
        votersLV=(ListView)findViewById(R.id.votersLV);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//getting data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

           thePlace =Integer.valueOf(extras.getString("thePlace"));
            answerIndex=Integer.valueOf(extras.getString("answerIndex"));
        }

        queTV.setText(user.getMyHistoryQuestions().get(thePlace).getQue());
        String ans = user.getMyHistoryQuestions().get(thePlace).getAnsByIndex(answerIndex);
        ansTV.setText("Those voted ~~~"+ans);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //showList
        items =user.getMyHistoryQuestions().get(answerIndex).getVotersForAnsByIndex(answerIndex);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        votersLV.setAdapter(adapter);
    }

    public void back(View view)
    {
        finish();
    }
}