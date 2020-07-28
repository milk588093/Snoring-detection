package com.example.project1220.ui.notifications;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class plot
{
    public void graphInit(LineChart myChart)
    {
        myChart.setNoDataText("No data for the moment");
        myChart.setHighlightPerTapEnabled(true);
        myChart.setTouchEnabled(true);
        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(true);
        myChart.setDrawGridBackground(false);
        myChart.setPinchZoom(true);
        myChart.setBackgroundColor(Color.TRANSPARENT);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);
        myChart.setData(data);

        Legend l = myChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.BLACK);
        XAxis xl = myChart.getXAxis();


        LimitLine a455=new LimitLine(22435,"43");
        a455.setLineColor(Color.RED);
        a455.setLineWidth(4);
        xl.addLimitLine(a455);

        LimitLine a44=new LimitLine(18930,"42");
        a44.setLineColor(Color.RED);
        a44.setLineWidth(4);
        xl.addLimitLine(a44);

        LimitLine a43=new LimitLine(18427,"41");
        a43.setLineColor(Color.RED);
        a43.setLineWidth(4);
        xl.addLimitLine(a43);

        LimitLine a42=new LimitLine(17875,"40");
        a42.setLineColor(Color.RED);
        a42.setLineWidth(4);
        xl.addLimitLine(a42);

        LimitLine a41=new LimitLine(17348,"39");
        a41.setLineColor(Color.RED);
        a41.setLineWidth(4);
        xl.addLimitLine(a41);

        LimitLine a400=new LimitLine(16922,"38");
        a400.setLineColor(Color.RED);
        a400.setLineWidth(4);
        xl.addLimitLine(a400);

        LimitLine a40=new LimitLine(16517,"37");
        a40.setLineColor(Color.RED);
        a40.setLineWidth(4);
        xl.addLimitLine(a40);

        LimitLine a39=new LimitLine(16095,"36");
        a39.setLineColor(Color.RED);
        a39.setLineWidth(4);
        xl.addLimitLine(a39);

        LimitLine a38=new LimitLine(15580,"35");
        a38.setLineColor(Color.RED);
        a38.setLineWidth(4);
        xl.addLimitLine(a38);

        LimitLine a37=new LimitLine(15125,"34");
        a37.setLineColor(Color.RED);
        a37.setLineWidth(4);
        xl.addLimitLine(a37);

        LimitLine a36=new LimitLine(14675,"33");
        a36.setLineColor(Color.RED);
        a36.setLineWidth(4);
        xl.addLimitLine(a36);


        LimitLine a35=new LimitLine(14218,"32");
        a35.setLineColor(Color.RED);
        a35.setLineWidth(4);
        xl.addLimitLine(a35);

        LimitLine a34=new LimitLine(13720,"31");
        a34.setLineColor(Color.RED);
        a34.setLineWidth(4);
        xl.addLimitLine(a34);

        LimitLine a33=new LimitLine(13260,"30");
        a33.setLineColor(Color.RED);
        a33.setLineWidth(4);
        xl.addLimitLine(a33);

        LimitLine a32=new LimitLine(12665,"29");
        a32.setLineColor(Color.RED);
        a32.setLineWidth(4);
        xl.addLimitLine(a32);

        LimitLine a31=new LimitLine(12194,"28");
        a31.setLineColor(Color.RED);
        a31.setLineWidth(4);
        xl.addLimitLine(a31);


        LimitLine a30=new LimitLine(11752,"27");
        a30.setLineColor(Color.RED);
        a30.setLineWidth(4);
        xl.addLimitLine(a30);

        LimitLine a29=new LimitLine(11268,"26");
        a29.setLineColor(Color.RED);
        a29.setLineWidth(4);
        xl.addLimitLine(a29);

        LimitLine a28=new LimitLine(10760,"25");
        a28.setLineColor(Color.RED);
        a28.setLineWidth(4);
        xl.addLimitLine(a28);

        LimitLine a27=new LimitLine(10222,"24");
        a27.setLineColor(Color.RED);
        a27.setLineWidth(4);
        xl.addLimitLine(a27);

        LimitLine a26=new LimitLine(9696,"23");
        a26.setLineColor(Color.RED);
        a26.setLineWidth(4);
        xl.addLimitLine(a26);




        LimitLine a25=new LimitLine(9270,"22");
        a25.setLineColor(Color.RED);
        a25.setLineWidth(4);
        xl.addLimitLine(a25);

        LimitLine a24=new LimitLine(8710,"21");
        a24.setLineColor(Color.RED);
        a24.setLineWidth(4);
        xl.addLimitLine(a24);

        LimitLine a23=new LimitLine(8405,"20");
        a23.setLineColor(Color.RED);
        a23.setLineWidth(4);
        xl.addLimitLine(a23);

        LimitLine a22=new LimitLine(8256,"19");
        a22.setLineColor(Color.RED);
        a22.setLineWidth(4);
        xl.addLimitLine(a22);

        LimitLine a21=new LimitLine(7765,"18");
        a21.setLineColor(Color.RED);
        a21.setLineWidth(4);
        xl.addLimitLine(a21);


        LimitLine a20=new LimitLine(7354,"17");
        a20.setLineColor(Color.RED);
        a20.setLineWidth(4);
        xl.addLimitLine(a20);

        LimitLine a19=new LimitLine(6876,"16");
        a19.setLineColor(Color.RED);
        a19.setLineWidth(4);
        xl.addLimitLine(a19);

        LimitLine a18=new LimitLine(6376,"15");
        a18.setLineColor(Color.RED);
        a18.setLineWidth(4);
        xl.addLimitLine(a18);

        LimitLine a17=new LimitLine(5882,"14");
        a17.setLineColor(Color.RED);
        a17.setLineWidth(4);
        xl.addLimitLine(a17);

        LimitLine a16=new LimitLine(5393,"13");
        a16.setLineColor(Color.RED);
        a16.setLineWidth(4);
        xl.addLimitLine(a16);





        LimitLine a15=new LimitLine(4909,"12");
        a15.setLineColor(Color.RED);
        a15.setLineWidth(4);
        xl.addLimitLine(a15);

        LimitLine a14=new LimitLine(4477,"11");
        a14.setLineColor(Color.RED);
        a14.setLineWidth(4);
        xl.addLimitLine(a14);

        LimitLine a13=new LimitLine(3956,"10");
        a13.setLineColor(Color.RED);
        a13.setLineWidth(4);
        xl.addLimitLine(a13);


        LimitLine a12=new LimitLine(3484,"9");
        a12.setLineColor(Color.RED);
        a12.setLineWidth(4);
        xl.addLimitLine(a12);

        LimitLine a11=new LimitLine(3186,"8");
        a11.setLineColor(Color.RED);
        a11.setLineWidth(4);
        xl.addLimitLine(a11);

        LimitLine a9=new LimitLine(2974,"7");
        a9.setLineColor(Color.RED);
        a9.setLineWidth(4);
        xl.addLimitLine(a9);

        LimitLine a8=new LimitLine(2624,"6");
        a8.setLineColor(Color.RED);
        a8.setLineWidth(4);
        xl.addLimitLine(a8);

        LimitLine B7=new LimitLine(2536,"5");
        B7.setLineColor(Color.RED);
        B7.setLineWidth(4);
        xl.addLimitLine(B7);

        LimitLine  B6=new LimitLine(2084,"4");
        B6.setLineColor(Color.RED);
        B6.setLineWidth(4);
        xl.addLimitLine(B6);


        LimitLine B5=new LimitLine(1597,"3");
        B5.setLineColor(Color.RED);
        B5.setLineWidth(4);
        xl.addLimitLine( B5);

        LimitLine B4=new LimitLine(1308,"2");
        B4.setLineColor(Color.RED);
        B4.setLineWidth(4);
        xl.addLimitLine(B4);



       LimitLine B3=new LimitLine(1146,"1");
        B3.setLineColor(Color.RED);
        B3.setLineWidth(4);
        xl.addLimitLine(B3);



        xl.setTextColor(Color.BLACK);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setTextSize(6);

        YAxis yl = myChart.getAxisLeft();
        yl.setTextColor(Color.BLACK);
        yl.setAxisMaxValue(900f); //max y range in graph
        yl.setAxisMinValue(0f); //max x range in graph
        yl.setDrawGridLines(true);

        YAxis yl2 = myChart.getAxisRight();
        yl2.setEnabled(false);
    }

    public void addEntry(LineChart myChart, ArrayList<Float> myFloat)
    {
        LineData data = myChart.getData();
        LineData data2 = myChart.getData();
        LineDataSet set2 = (LineDataSet)data.getDataSetByIndex(1);
        if (data != null)
        {
            LineDataSet set = (LineDataSet)data.getDataSetByIndex(0);


            if (set == null)
            {
                set = createSet();
                data.addDataSet(set);
            }
            set2 = createSet2();
            data.addDataSet(set2);
            if(myFloat==null)
            {
                Log.d("Plot: ","No previous recorded data");

            }else
                {
                for (int i=0; i<myFloat.size();i++)
                {
                    data.addEntry(new Entry(set.getEntryCount(),(float)myFloat.get(i)), 0);
                   data2.addEntry(new Entry(set2.getEntryCount(),0), 1);


                }
            }
        }












        myChart.notifyDataSetChanged();
        myChart.setVisibleXRangeMaximum(1000);
        myChart.moveViewToX(data.getEntryCount());

    }

    private LineDataSet createSet()
    {
        LineDataSet set = new LineDataSet(null,"鼾聲訊號");
        set.setDrawCircles(false);
        set.setCubicIntensity(0);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(2);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 177));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(10f);

        return set;
    }
    private LineDataSet createSet2()
    {


        LineDataSet set2 = new LineDataSet(null,"呼吸中止次數");
        set2.setDrawCircles(false);
        set2.setCubicIntensity(0);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.RED);
        set2.setLineWidth(2f);
        set2.setCircleSize(2);
        set2.setFillAlpha(65);
        set2.setFillColor(Color.RED);
        set2.setHighLightColor(Color.rgb(244, 117, 177));
        set2.setValueTextColor(Color.BLACK);
        set2.setValueTextSize(10f);

        return set2;
    }
    public void clearGraph(LineChart myChart)
    {
        myChart.clearValues();
        graphInit(myChart);
    }
}
