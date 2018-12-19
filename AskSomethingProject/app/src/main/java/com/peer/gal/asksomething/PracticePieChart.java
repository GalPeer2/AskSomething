package com.peer.gal.asksomething;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

//https://www.codingdemos.com/android-pie-chart-tutorial/

public class PracticePieChart extends AppCompatActivity {

    User user;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;
    Question theQuestionDealed;
    PieChartView pieChartView;
    int thePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pie_chart);


        pieChartView = (PieChartView)findViewById(R.id.chart);

        List<SliceValue> pieData = new ArrayList<>();
        theStateMgr = new StateMgr (this );
        asklSomeThingState= theStateMgr.LoadState();
        user=asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        if (user.getMyHistoryQuestions().size()==0) {
            Toast.makeText(PracticePieChart.this, "you dont have questions to show", Toast.LENGTH_SHORT).show();
            return;
        }

       thePlace=user.getMyHistoryQuestions().size()-1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thePlace =Integer.parseInt(extras.getString("thePlace"));}




        theQuestionDealed =user.getMyHistoryQuestions().get(thePlace);

   //exampleQuestion
   /*   theQuestionDealed=  new Question("????","a","b","c","d");
        ArrayList<String> s = new ArrayList<String>();
        s.add("str1");
        s.add("str hello");
        s.add("str bye");
        theQuestionDealed.setVotersForAns1(s);
        ArrayList<String> s2 =new ArrayList<>();
        s2.add("fgf");
        theQuestionDealed.setVotersForAns2(s2);
        theQuestionDealed.setVotersForAns3(s2);*/


        int v1=theQuestionDealed.getVotersForAns1().size();
        int v2=theQuestionDealed.getVotersForAns2().size();
        int v3=theQuestionDealed.getVotersForAns3().size();
        int v4=theQuestionDealed.getVotersForAns4().size();



    if (v1!=0)
        pieData.add(new SliceValue((100*v1)/(v1+v2+v3+v4), Color.BLUE).setLabel(theQuestionDealed.getAns1()+" : "+v1+" voters"));
        if (v2!=0)
        pieData.add(new SliceValue((v2*100)/(v1+v2+v3+v4), Color.GRAY).setLabel(theQuestionDealed.getAns2()+" : "+v2+" voters"));
        if (v3!=0)
        pieData.add(new SliceValue((v3*100)/(v1+v2+v3+v4), Color.RED).setLabel(theQuestionDealed.getAns3()+" : "+v3+" voters"));
       if (v4!=0)
        pieData.add(new SliceValue((v4*100)/(v1+v2+v3+v4), Color.GREEN).setLabel(theQuestionDealed.getAns4()+" : "+v4+" voters"));



        PieChartData pieChartData = new PieChartData(pieData);

        pieChartData.setHasLabels(true);

        pieChartData.setHasLabels(true).setValueLabelTextSize(14);

        pieChartView.setPieChartData(pieChartData);
/*
        imageView.setOnTouchListener(new OnSwipeTouchListener() {
            public void onSwipeTop() {
                Toast.makeText(MyActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(MyActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(MyActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(MyActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        */












    }
}
