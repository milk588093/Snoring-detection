package com.example.project1220.ui.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project1220.R;
import com.example.project1220.ui.home.HomeFragment;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotificationsFragment extends Fragment
{

    private NotificationsViewModel notificationsViewModel;
    ListView listViewRecord;
    TextView textView;


    ArrayAdapter arrayAdapter;
    ArrayList<String> recordDate;
    public static ArrayList<Float> odd;
    public static ArrayList<Float> number;
    public static ArrayList<Float> even;
    public static ArrayList<Float> count;


    public static String count11;
   
    public static Set<Float> set;





    public void dismissDialog()
    {
        arrayAdapter.clear();
        arrayAdapter.addAll(recordDate);
        arrayAdapter.notifyDataSetChanged();

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        listViewRecord = view.findViewById(R.id.listViewItembb);
        recordDate = new ArrayList<>();
        textView = view.findViewById(R.id.textaa);
//       textView2 = view.findViewById(R.id.textView2);
      // textView3 = view.findViewById(R.id.textView);
        // simple_list_item_1
        //textView.setText("Select record based on date and time\nLong click to delete record");
        arrayAdapter = new ArrayAdapter(getActivity(),R.layout.layout11,recordDate);
        listViewRecord.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        /*mChart = root.findViewById(R.id.graphLayout);
       myPlot = new plot();
       myPlot.graphInit(mChart);*/
        Cursor result = HomeFragment.myDb.getDateList();

       // textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
     // textView3.setMovementMethod(ScrollingMovementMethod.getInstance());










        if (result.getCount() == 0)
        {
            Toast.makeText(getActivity(),"Error: No data",Toast.LENGTH_SHORT).show();
            dismissDialog();
        }

        if (result.moveToFirst())
        {
            recordDate.add(result.getString(result.getColumnIndex("DATE")));
            while(result.moveToNext())
            {
                recordDate.add(result.getString(result.getColumnIndex("DATE")));
            }
        }


        listViewRecord.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Cursor result = HomeFragment.myDb.getData(recordDate.get(position));
                if (result.getCount() == 0)
                {
                    Toast.makeText(getActivity(),"Error: No data",Toast.LENGTH_SHORT).show();
                    dismissDialog();
                    return;
                }

                StringBuffer bufferRecord = new StringBuffer();

                while (result.moveToNext())
                {
                    bufferRecord.append(result.getString(0));


                }


               HomeFragment.recordedData = new ArrayList<>();

                int a=0;
                even=new ArrayList<>();
               number=new ArrayList<>();
               set = new HashSet<Float>(number);
               count=new ArrayList<>();
               odd=new ArrayList<>();



               for (String s : bufferRecord.toString().split("A"))
               {
                   even.add(Float.parseFloat(s));

               }

                    for (int i = 0; i < even.size(); i++)
                    {
                        if(i%2==0)
                        {
                            odd.add(even.get(i));

                        }
                        else
                            {
                                number.add(even.get(i));
                               // textView3.setText(String.valueOf(number));
                            }
                    }








//                Drawable drawable = ContextCompat.getDrawable(null, R.drawable.fade_red);
                ArrayList<Entry> values = new ArrayList<>();

                   /* long start=System.currentTimeMillis();
                    long stop=System.currentTimeMillis();
                    long hours = start- stop;*/
                for (int j = 0; j <number.size() ; j++)
                {


                            set.add(number.get(j));
                            count.clear();
                            count.addAll(set);


                }
                for(int j = 0; j <count.size() ; j++)
                {
                    if (count.get(j) >= 10 && count.get(j) <= 60)
                    {

                        a++;

                    }
                }










          count11=Integer.toString(a);

              //textView2.setText(String.valueOf(a));
                //textView3.setText(number.toString());


                 /*if(hours>3600000)
                    {
                        if(a<5)
                        {
                            textView4.setText("正常");
                        }
                        else if (a>=5&&a<=15)
                        {
                            textView4.setText("輕度");
                        }
                        else if (a>=15&&a<=30)
                        {

                            textView4.setText("中度");
                        }
                        else if (a>=30)
                        {
                            textView4.setText("重度");

                        }


                    }*/

               DialogFragment dialogFragment = new ploo();
                dialogFragment.show(getFragmentManager(),"ploo");

                //alertDialog.getWindow().setLayout(600, 400);
              /* new AlertDialog.Builder(getActivity())
                        .setPositiveButton("是"+ "", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                              myPlot.graphInit(mChart);
                              myPlot.addEntry(mChart,odd);

                            }

                        })
                        .show();*/

                /*NotificationsFragment.myPlot.graphInit(NotificationsFragment.mChart);
                NotificationsFragment.myPlot.addEntry(NotificationsFragment.mChart, NotificationsFragment.odd);*/


//                NotificationsFragment.myPlot.graphInit(NotificationsFragment.mChart);
                //NotificationsFragment.myPlot.addEntry(NotificationsFragment.mChart, NotificationsFragment.odd);
                   //dismissDialog();
                    Toast.makeText(getActivity(), "按記錄鍵重整", Toast.LENGTH_SHORT).show();
                }
        });











        listViewRecord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {

                new AlertDialog.Builder(getActivity())
                        .setTitle("")
                        .setMessage("確定刪除數據嗎?")
                        .setNegativeButton("否" , null)
                        .setPositiveButton("是"+ "", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                HomeFragment.myDb.deleteData(recordDate.get(position));
                                recordDate.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();

                return true;
            }
        });










        final TextView textView = view.findViewById(R.id.textaa);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

}