package com.peer.gal.asksomething;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
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
    LinearLayout linearLayout;
    int thePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pie_chart);


        pieChartView = (PieChartView) findViewById(R.id.chart);
        linearLayout=(LinearLayout)findViewById(R.id.theBigLayout) ;

        List<SliceValue> pieData = new ArrayList<>();
        theStateMgr = new StateMgr(this);
        asklSomeThingState = theStateMgr.LoadState();
        user = asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        if (user.getMyHistoryQuestions().size() == 0) {
            Toast.makeText(PracticePieChart.this, "you dont have questions to show", Toast.LENGTH_SHORT).show();
            return;
        }

        thePlace = user.getMyHistoryQuestions().size() - 1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thePlace = Integer.parseInt(extras.getString("thePlace"));
        }


        theQuestionDealed = user.getMyHistoryQuestions().get(thePlace);

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


        int v1 = theQuestionDealed.getVotersForAns1().size();
        int v2 = theQuestionDealed.getVotersForAns2().size();
        int v3 = theQuestionDealed.getVotersForAns3().size();
        int v4 = theQuestionDealed.getVotersForAns4().size();


        if (v1 != 0)
            pieData.add(new SliceValue((100 * v1) / (v1 + v2 + v3 + v4), Color.BLUE).setLabel(theQuestionDealed.getAns1() + " : " + v1 + " voters"));
        if (v2 != 0)
            pieData.add(new SliceValue((v2 * 100) / (v1 + v2 + v3 + v4), Color.GRAY).setLabel(theQuestionDealed.getAns2() + " : " + v2 + " voters"));
        if (v3 != 0)
            pieData.add(new SliceValue((v3 * 100) / (v1 + v2 + v3 + v4), Color.RED).setLabel(theQuestionDealed.getAns3() + " : " + v3 + " voters"));
        if (v4 != 0)
            pieData.add(new SliceValue((v4 * 100) / (v1 + v2 + v3 + v4), Color.GREEN).setLabel(theQuestionDealed.getAns4() + " : " + v4 + " voters"));


        PieChartData pieChartData = new PieChartData(pieData);

        pieChartData.setHasLabels(true);

        pieChartData.setHasLabels(true).setValueLabelTextSize(14);

        pieChartView.setPieChartData(pieChartData);

        linearLayout.setOnTouchListener(new OnSwipeTouchListener() {

            public void onSwipeRight() {
                if (thePlace==0)
                    return;
                Intent a =new Intent(PracticePieChart.this,PracticePieChart.class);
                a.putExtra("thePlace",thePlace-1);
                finish();
                startActivity(a);

            }
            public void onSwipeLeft() {
                if (thePlace==user.getMyHistoryQuestions().size()-1)
                    return;
                Intent a =new Intent(PracticePieChart.this,PracticePieChart.class);
                a.putExtra("thePlace",thePlace+1);
                finish();
                startActivity(a);
            }


            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });



        class OnSwipeTouchListener implements View.OnTouchListener {

            public final GestureDetector gestureDetector;

            public OnSwipeTouchListener(Context ctx) {
                gestureDetector = new GestureDetector(ctx, new GestureListener());
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }

            class GestureListener extends GestureDetector.SimpleOnGestureListener {

                private static final int SWIPE_THRESHOLD = 100;
                private static final int SWIPE_VELOCITY_THRESHOLD = 100;

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    boolean result = false;
                    try {
                        float diffX = e2.getX() - e1.getX();

                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;

                        result = true;

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return result;
                }
            }

            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
            }

        }
    }
}
