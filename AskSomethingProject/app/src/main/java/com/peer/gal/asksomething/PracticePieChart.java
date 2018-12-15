package com.peer.gal.asksomething;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

//https://www.codingdemos.com/android-pie-chart-tutorial/

public class PracticePieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pie_chart);

        PieChartView pieChartView = (PieChartView)findViewById(R.id.chart);
        List<SliceValue> pieData = new ArrayList<>();
/*
        pieData.add(new SliceValue(15, Color.BLUE));
        pieData.add(new SliceValue(25, Color.GRAY));
        pieData.add(new SliceValue(10, Color.RED));
        pieData.add(new SliceValue(60, Color.MAGENTA));
*/



        pieData.add(new SliceValue(100, Color.BLUE).setLabel("Q1: $10"));
        pieData.add(new SliceValue(1, Color.GRAY).setLabel("Q2: $4"));
        pieData.add(new SliceValue(1, Color.RED).setLabel("Q3: $18"));
        pieData.add(new SliceValue(1, Color.MAGENTA).setLabel("Q4: $28"));


        PieChartData pieChartData = new PieChartData(pieData);


        pieChartData.setHasLabels(true);

        pieChartData.setHasLabels(true).setValueLabelTextSize(14);


        pieChartView.setPieChartData(pieChartData);

    }
}
