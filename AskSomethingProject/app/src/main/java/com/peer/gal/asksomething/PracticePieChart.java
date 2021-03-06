package com.peer.gal.asksomething;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
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
    ImageView swipeImV;
    int thePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pie_chart);


        pieChartView = (PieChartView) findViewById(R.id.chart);
        linearLayout = (LinearLayout) findViewById(R.id.theBigLayout);
        swipeImV = (ImageView) findViewById(R.id.swipe);

        theStateMgr = new StateMgr(this);
        asklSomeThingState = theStateMgr.LoadState();
        user = asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());
/*
        theQuestionDealed=  new Question("secque","a","b","c","d");
        ArrayList<String> s = new ArrayList<String>();
        s.add("str1");
        s.add("str hello");
        s.add("str bye");
        theQuestionDealed.setVotersForAns1(s);
        ArrayList<String> s2 =new ArrayList<>();
        s2.add("fgf");
        theQuestionDealed.setVotersForAns2(s2);
        theQuestionDealed.setVotersForAns3(s);

        user.getMyHistoryQuestions().add(theQuestionDealed);
        theStateMgr.SaveState(asklSomeThingState);
        */


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

        LoadQuestion(theQuestionDealed);



        linearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            //SLIDING

            public void onSwipeRight() {
                if (thePlace == 0) {
                    Toast.makeText(PracticePieChart.this, "first!", Toast.LENGTH_SHORT/2).show();
                    return;
                }
                thePlace--;
                LoadQuestion(user.getMyHistoryQuestions().get(thePlace));////////////////////////////


            }

            public void onSwipeLeft() {

                if (thePlace == user.getMyHistoryQuestions().size() - 1) {
                    Toast.makeText(PracticePieChart.this, "last!", Toast.LENGTH_SHORT/2).show();
                    return;
                }
                thePlace++;
                LoadQuestion(user.getMyHistoryQuestions().get(thePlace));
            }


            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            // choosing slice and move to ShowVOTERS
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                if (String.valueOf(value.getLabelAsChars()).equals("no voters")) {
                    Toast.makeText(PracticePieChart.this, String.valueOf(value.getLabelAsChars()) + " yet", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent a = new Intent(PracticePieChart.this, ShowVoters.class);
                switch (value.getColor()) {
                    case (Color.BLUE): {
                        a.putExtra("answerIndex", "1");
                        break;
                    }
                    case (Color.GRAY): {
                        a.putExtra("answerIndex", "2");
                        break;
                    }
                    case (Color.RED): {
                        a.putExtra("answerIndex", "3");
                        break;
                    }
                    case (Color.GREEN): {
                        a.putExtra("answerIndex", "4");
                        break;
                    }
                }

                a.putExtra("thePlace", "" + thePlace);
                startActivity(a);
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }




/*
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

    */

    public void LoadQuestion(Question theQuestionDealed)
    {

        List<SliceValue> pieData = new ArrayList<>();
        int v1 = theQuestionDealed.getVotersForAns1().size();
        int v2 = theQuestionDealed.getVotersForAns2().size();
        int v3 = theQuestionDealed.getVotersForAns3().size();
        int v4 = theQuestionDealed.getVotersForAns4().size();


        if (v1 != 0)
            pieData.add(new SliceValue((100 * v1) / (v1 + v2 + v3 + v4), Color.BLUE).setLabel(theQuestionDealed.getAns1() + " : " + v1 + " voters"));
        if (v2 != 0)
            pieData.add(new SliceValue((100* v2) / (v1 + v2 + v3 + v4), Color.GRAY).setLabel(theQuestionDealed.getAns2() + " : " + v2 + " voters"));
        if (v3 != 0)
            pieData.add(new SliceValue((v3 * 100) / (v1 + v2 + v3 + v4), Color.RED).setLabel(theQuestionDealed.getAns3() + " : " + v3 + " voters"));
        if (v4 != 0)
            pieData.add(new SliceValue((v4 * 100) / (v1 + v2 + v3 + v4), Color.GREEN).setLabel(theQuestionDealed.getAns4() + " : " + v4 + " voters"));
        if ((v1+v2+v3+v4)==0)
            pieData.add(new SliceValue(100,Color.BLACK).setLabel("no voters"));


        PieChartData pieChartData = new PieChartData(pieData);

        pieChartData.setHasLabels(true);

        pieChartData.setHasLabels(true).setValueLabelTextSize(10);

        pieChartData.setHasCenterCircle(true).setCenterText1( /*downEvery4Words*/(theQuestionDealed.getQue()));
        pieChartData.setCenterText1FontSize(12);

        pieChartView.setPieChartData(pieChartData);

    }

    public void reLoadQuestion(View view)
    {
        asklSomeThingState = theStateMgr.LoadState();
        user = asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());
        LoadQuestion(user.getMyHistoryQuestions().get(thePlace));
    }
    public void backWithIntent(View v)
    {
        startActivity(new Intent(PracticePieChart.this,ShowQuestions.class));
    }
    public void deleteQuestion (View view)
    {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setMessage("ATTENTION: by choosing ''Delete'' all data of this question would be earased and your contants wouldnt be able to answer it anymore."+"\n"
                +"Are you sure you want to delete '' "+user.getMyHistoryQuestions().get(thePlace).getQue()+" '' ?");
        a.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                user.getMyHistoryQuestions().remove(thePlace);
                theStateMgr.SaveState(asklSomeThingState);
                Toast.makeText(PracticePieChart.this, "Question deleted succesfully", Toast.LENGTH_SHORT).show();

                if (thePlace==user.getMyHistoryQuestions().size())
                    thePlace--;
                if (thePlace == -1)
                { backWithIntent(null);return;}
                reLoadQuestion(null);

            }
        });
        a.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = a.create();
        alertDialog.show();
    }


    public String downEvery4Words(String s)
    {
        String theFinalString ="";
        String [] strings = s.split(" ");
        int i=0;
        while (strings.length>i+4);
        {
            theFinalString+=strings[i];
            theFinalString+=strings[i+1];
            theFinalString+=strings[i+2];
            theFinalString+=strings[i+3];
            theFinalString+="\n";
            i=+4;
        }
        while (i<strings.length)
        {
            theFinalString+=strings[i];
            i++;
        }
        return theFinalString;


    }
}
