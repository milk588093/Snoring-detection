package com.example.project1220.ui.notifications;

import android.graphics.Bitmap;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

class ImageLineChartRenderer extends LineChartRenderer
{

    private final LineChart lineChart;
    private final Bitmap image;

    public ImageLineChartRenderer(LineChart mChart, ChartAnimator animator, ViewPortHandler viewPortHandler, Bitmap starBitmap) {
        super(mChart, animator, viewPortHandler);
        this.lineChart = mChart;
        this.image = starBitmap;
    }
}
