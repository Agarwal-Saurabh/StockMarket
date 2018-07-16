package com.example.saurabhagarwal.stockmarket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.saurabhagarwal.stockmarket.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        init();
    }

    private void init() {
        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, Double.parseDouble(Result.res[0])),
                new DataPoint(1,Double.parseDouble(Result.res[1])),
                new DataPoint(2, Double.parseDouble(Result.res[2])),
                new DataPoint(3, Double.parseDouble(Result.res[3])),
                new DataPoint(4, Double.parseDouble(Result.res[4])),
                new DataPoint(5, Double.parseDouble(Result.res[5])),
                new DataPoint(6, Double.parseDouble(Result.res[6]))

              /*  new DataPoint(0, 5),
                new DataPoint(1,3),
                new DataPoint(2, 5),
                new DataPoint(3, 9),
                new DataPoint(4, 3),
                new DataPoint(5, 7),
                new DataPoint(6, 4)*/
        });
        graph.addSeries(series);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Day");
        gridLabel.setVerticalAxisTitle("Closing");

       /* StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);*/
    }
}
