package com.peer.gal.asksomething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.util.ArrayList;

public class ShowQuestions extends AppCompatActivity {

    ListView listView;
    User user;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questions);

        theStateMgr = new StateMgr(this );
        asklSomeThingState= theStateMgr.LoadState();
        user=asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        listView =(ListView)findViewById(R.id.listQueLV);

        items=new ArrayList<String>();
        for (Question question:user.getMyHistoryQuestions())
        {
            items.add(question.getQue());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);

    }
}
