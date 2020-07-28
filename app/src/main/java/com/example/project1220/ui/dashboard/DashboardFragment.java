package com.example.project1220.ui.dashboard;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project1220.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DashboardFragment extends Fragment implements DatePickerDialog.OnDateSetListener
{
    private DashboardViewModel dashboardViewModel;



    private BarChart chart;

    Button button;
    TextView textView77;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //button=root.findViewById(R.id.button111);
        textView77 = root.findViewById(R.id.textView888);
        chart = root.findViewById(R.id.chart1);

        //  ploo.myPlot.addEntry(ploo.mChart, NotificationsFragment.odd);
        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);
        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        XAxis xl = chart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setValueFormatter(xAxisFormatter);
        xl.setGranularity(1f);
        xl.setLabelCount(7);


        YAxis yl = chart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        yl.setAxisMaxValue(200f); //max y range in graph
        yl.setAxisMinValue(0f); //max x range in graph
        yl.setDrawGridLines(true);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(10f);
        l.setXEntrySpace(4f);
        l.setTextColor(Color.WHITE);



        chart.animateY(1000);
        setData(5,12);
        chart.invalidate();





/*        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getChildFragmentManager(), "date picker");

            }
        });*/




        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });


        return root;


    }




    private void setData(int count, float range)
    {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2= new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();
        for (int i = (int) start; i < start + count; i++)
        {
            int val= (int) (50*Math.random()-10);

            if (val<5)
            {
                values.add(new BarEntry(i, val));

               //values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));

            }
            else if(val>=5&&val<=10)
            {
                values1.add(new BarEntry(i, val));

            }

            else if(val>=15&&val<=30)
            {
                values2.add(new BarEntry(i, val));
            }

            else if(val>=30)
            {
                values3.add(new BarEntry(i, val));
            }



        }

        BarDataSet set1;
        BarDataSet set2;
        BarDataSet set3;
        BarDataSet set4;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
           set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set3 = (BarDataSet) chart.getData().getDataSetByIndex(2);
            set4 = (BarDataSet) chart.getData().getDataSetByIndex(3);

            set1.setValues(values);
            set2.setValues(values1);
            set3.setValues(values2);
            set4.setValues(values3);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else
            {
            set1 = new BarDataSet(values, "正常");
            set2 = new BarDataSet(values1, "輕度");
            set3 = new BarDataSet(values2, "中度");
            set4 = new BarDataSet(values3, "重度");

            set1.setValueTextColor(Color.WHITE);
            set2.setValueTextColor(Color.WHITE);
            set3.setValueTextColor(Color.WHITE);
            set4.setValueTextColor(Color.WHITE);

            set1.setDrawIcons(true);


            set1.setColor(Color.parseColor("#02DF82"));
            set2.setColor(Color.parseColor("#F9F900"));
            set3.setColor(Color.parseColor("#FF8000"));
            set4.setColor(Color.parseColor("#FF0000"));



            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);
            dataSets.add(set4);
            BarData data = new BarData(dataSets);

            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            chart.setData(data);
        }



    }



























    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        /*c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.setFirstDayOfWeek(Calendar.MONDAY);*/


        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());



        textView77.setText(currentDateString );

    }
















   /* public void initGraph(GraphView graph)
    {
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(30);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(500);

        // first mSeries is a line
        mSeries = new LineGraphSeries<>();
        graph.addSeries(mSeries);
    }
    public void onResume()
    {
        mTimer = new Runnable()
        {
            @Override
            public void run()
            {
                if (graphLastXValue == 40)
                {
                    graphLastXValue = 0;
                    mSeries.resetData(new DataPoint[]{
                            new DataPoint(graphLastXValue, getRandom())
                    });
                }
                graphLastXValue += 1d;
                mSeries.appendData(new DataPoint(graphLastXValue, getRandom()), false, 40);
                mHandler.postDelayed(this, 50);
            }
        };
        mHandler.postDelayed(mTimer, 700);
        super.onResume();
    }
    public void onPause()
    {
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    double mLastRandom = 2;

    private double getRandom()
    {
        mLastRandom++;
        return Math.sin(mLastRandom*0.5) * 10 * (Math.random() * 10 + 1);
    }*/











}