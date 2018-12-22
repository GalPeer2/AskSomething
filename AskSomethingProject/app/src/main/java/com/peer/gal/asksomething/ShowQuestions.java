package com.peer.gal.asksomething;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ShowQuestions.this,PracticePieChart.class);
                intent.putExtra("thePlace",""+position);
                startActivity(intent);
            }

        });

    }
}
