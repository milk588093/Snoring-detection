package com.example.project1220.ui.notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.project1220.R;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class ploo extends DialogFragment
{
    static public plot myPlot;
    static public LineChart mChart;
    static public TextView BB;
    static public TextView CC;

    public static ArrayList<Float> tired;
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        int A;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.plot111,null);
        mChart = view.findViewById(R.id.graphLayout);
        BB=view.findViewById(R.id.textView6);
        CC=view.findViewById(R.id.textViewjj);
        myPlot = new plot();
       myPlot.graphInit(mChart);
      ploo.myPlot.graphInit(ploo.mChart);
       ploo.myPlot.addEntry(ploo.mChart, NotificationsFragment.odd);
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);





      //  ploo.myPlot.addEntry(ploo.mChart, NotificationsFragment.count);
        BB.setText( String.valueOf(NotificationsFragment.count11));
        BB.setMovementMethod(ScrollingMovementMethod.getInstance());
        BB.setTextColor(Color.parseColor("#000000"));

       CC.setMovementMethod(ScrollingMovementMethod.getInstance());















      A=Integer.parseInt(NotificationsFragment.count11);

        if(A<5)
        {
            CC.setText("正常");
            CC.setTextColor(Color.parseColor("#02DF82"));
        }
        else if (A>=5&&A<=15)
        {
            CC.setText("輕度");
            CC.setTextColor(Color.parseColor("#F9F900"));
        }
        else if (A>=15&&A<=30)
        {

            CC.setText("中度");
            CC.setTextColor(Color.parseColor("#FF8000"));
        }
        else if (A>=30)
        {
            CC.setText("重度");
            CC.setTextColor(Color.parseColor("#FF0000"));

        }



        builder.setView(view);


        return builder.create();


    }




}
