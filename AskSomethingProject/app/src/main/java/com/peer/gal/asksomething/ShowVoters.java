package com.peer.gal.asksomething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

public class ShowVoters extends AppCompatActivity {
    User user;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    int thePlace=0;
    int answerIndex=1;
    TextView queTV,ansTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_voters);

        theStateMgr = new StateMgr(this);
        asklSomeThingState = theStateMgr.LoadState();
        user = asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        queTV=(TextView)findViewById(R.id.queTV);
        ansTV=(TextView)findViewById(R.id.ansTV);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thePlace = Integer.parseInt(extras.getString("thePlace"));
            answerIndex=Integer.parseInt(extras.getString("answerIndex"));
        }
        queTV.setText(user.getMyHistoryQuestions().get(thePlace).getQue());
        String ans="error";
        switch (answerIndex)
        {
            case (1):
            {ans=user.getMyHistoryQuestions().get(thePlace).getAns1();break;}
            case (2):
            {ans=user.getMyHistoryQuestions().get(thePlace).getAns2();break;}
            case (3):
            {ans=user.getMyHistoryQuestions().get(thePlace).getAns3();break;}
            case (4):
            {ans=user.getMyHistoryQuestions().get(thePlace).getAns4();break;}

        }
        ansTV.setText("Those voted ~~~"+ans);
    }
}
