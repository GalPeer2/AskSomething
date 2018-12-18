package com.peer.gal.asksomething;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

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
    int thePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pie_chart);
/*
        theStateMgr = new StateMgr (this );
        asklSomeThingState= theStateMgr.LoadState();
        user=asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        thePlace=user.getMyHistoryQuestions().size()-1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thePlace =Integer.parseInt(extras.getString("thePlace"));
        }



        theQuestionDealed =user.getMyHistoryQuestions().get(thePlace);
        */
      theQuestionDealed=  new Question("????","a","b","c","d");
        ArrayList<String> s = new ArrayList<String>();
        s.add("str1");
        s.add("str hello");
        s.add("str bye");
        theQuestionDealed.setVotersForAns1(s);
        ArrayList<String> s2 =new ArrayList<>();
        s2.add("fgf");
        theQuestionDealed.setVotersForAns2(s2);
       // theQuestionDealed.setVotersForAns3(s2);

        PieChartView pieChartView = (PieChartView)findViewById(R.id.chart);
        List<SliceValue> pieData = new ArrayList<>();

        int v1=theQuestionDealed.getVotersForAns1().size();
        int v2=theQuestionDealed.getVotersForAns2().size();
        int v3=theQuestionDealed.getVotersForAns3().size();
        int v4=theQuestionDealed.getVotersForAns4().size();

        v1=3;
        v2=0;
        v3=0;
        v4=0;


    if (v1!=0)
        pieData.add(new SliceValue((v1)/(v1+v2+v3+v4)*100, Color.BLUE).setLabel(theQuestionDealed.getAns1()+" : "+v1+" voters"));
        if (v2!=0)
        pieData.add(new SliceValue((v2)/(v1+v2+v3+v4)*100, Color.GRAY).setLabel(theQuestionDealed.getAns2()+" : "+v2+" voters"));
        if (v3!=0)
        pieData.add(new SliceValue((v3)/(v1+v2+v3+v4)*100, Color.RED).setLabel(theQuestionDealed.getAns3()+" : "+v3+" voters"));
       if (v4!=0)
        pieData.add(new SliceValue((v4)/(v1+v2+v3+v4)*100, Color.MAGENTA).setLabel(theQuestionDealed.getAns4()+" : "+v4+" voters"));


       pieData.add(new SliceValue(100,Color.RED).setLabel("kkkaka"));


        PieChartData pieChartData = new PieChartData(pieData);


        pieChartData.setHasLabels(true);

        pieChartData.setHasLabels(true).setValueLabelTextSize(14);


        pieChartView.setPieChartData(pieChartData);




    }
}
