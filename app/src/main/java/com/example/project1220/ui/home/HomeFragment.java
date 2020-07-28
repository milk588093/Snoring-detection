package com.example.project1220.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project1220.AlertReceiver;
import com.example.project1220.DatabaseHelper;
import com.example.project1220.R;
import com.example.project1220.TimePickerFragment;
import com.example.project1220.ui.notifications.koo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import at.markushi.ui.CircleButton;

public class HomeFragment extends Fragment implements TimePickerDialog.OnTimeSetListener
{

    String TAG = "HomeFragment";

    public static DatabaseHelper myDb;
    public static ArrayList<Float> recordedData;

    static BluetoothAdapter myBluetoothAdapter;
    static int connectState;
    public static  ToggleButton alarm11;
    public static  ToggleButton bt11;
    static TextView textViewStream;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    HomeViewModel homeViewModel;
    //ToggleButton bt11;
    //ToggleButton alarm11;
    Button button;
    CircleButton start11;
    TextView text11;
    //TextView text18;
    private final BroadcastReceiver BroadcastReceiver1 = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (action.equals(myBluetoothAdapter.ACTION_STATE_CHANGED))
            {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, myBluetoothAdapter.ERROR);

                switch(state)
                {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING ON");
                        break;
                }
            }
        }
    };


    private void enableBT()
    {
        if(myBluetoothAdapter == null)
        {
            Log.d(TAG, "enableBT: Does not have BT capabilities.");
        }
        if(!myBluetoothAdapter.isEnabled())
        {
            Log.d(TAG, "enableBT: enabling BT.");
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            getActivity().registerReceiver(BroadcastReceiver1, BTIntent);

            while (!myBluetoothAdapter.isEnabled())
            {
                if (myBluetoothAdapter.isEnabled())
                {
                    openBTHelperDialog();
                }
            }
        }
    }
    private void disableBT()
    {
        if(myBluetoothAdapter == null)
        {
            Log.d(TAG, "enableBT: Does not have BT capabilities.");

        }
        if(myBluetoothAdapter.isEnabled())
        {
            Log.d(TAG, "enableDisableBT: disabling BT.");
            myBluetoothAdapter.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            getActivity().registerReceiver(BroadcastReceiver1, BTIntent);
        }

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        connectState = 0;
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        View root = inflater.inflate(R.layout.fragment_home, null);

        alarm11= root.findViewById(R.id.alarm);
        start11 = root.findViewById(R.id.start);
        bt11 =  root.findViewById(R.id.bt);


        myDb = new DatabaseHelper(getActivity());
        textViewStream = root.findViewById(R.id.textViewStream);

        text11= root.findViewById(R.id.textView9);
       // text18= root.findViewById(R.id.textView778);
        // button2 = root.findViewById(R.id.buttonalarm);
        // button2.setEnabled(false);

       start11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                new AlertDialog.Builder(getActivity())
                    .setMessage("配對藍芽與鬧鐘設置")
                    .setPositiveButton("未完成"+ "", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }

                    })
                        .setNegativeButton("完成"+ "", new DialogInterface.OnClickListener()
                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            if(alarm11.isChecked())
                            {
                                //BTDialog2.Connect();
                                Toast.makeText(getActivity(), "睡覺吧", Toast.LENGTH_SHORT).show();


                                DialogFragment dialogFragment = new koo();
                                dialogFragment.show(getFragmentManager(), "koo");

                            }
                            else
                            {
                                Toast.makeText(getActivity(), "未設定鬧鐘", Toast.LENGTH_SHORT).show();

                            }

                            }

                        })
                    .show();






            }
        });

                alarm11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if(alarm11.isChecked())
                {

               DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getChildFragmentManager(),  "time picker");
                    Toast.makeText(getActivity(), "按開始鍵偵測", Toast.LENGTH_SHORT).show();


                    /*text18.setText(BTDialog1.S);
                    text18.setTextColor(Color.parseColor("#FFFFFF"));*/
                   /* Intent intent = new Intent(getActivity(),alarm.class);
                    startActivity(intent);*/

            }

                else
                    {
                        cancelAlarm();
                       FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("koo");
                        ft.remove(prev);
                        //text18.setText("");
                }

            }
        });


        bt11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if ( bt11.isChecked())
                {
                    start();
                    Toast.makeText(getActivity(),"藍芽連接",Toast.LENGTH_SHORT).show();

            }
                else{
                   //BTDialog2.stopConnection();
                    // disableBT();
                    Toast.makeText(getActivity(),"已關閉藍芽",Toast.LENGTH_SHORT).show();

                }

            }
        });


        return root;

    }


    private void updateTimeText(Calendar c)
    {
        String timeText = "設定起床時間 ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
       text11.setText(timeText);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);

    }

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
   private void startAlarm(Calendar c)
    {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent( getActivity(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast( getActivity(), 1, intent, 0);

        if (c.before(Calendar.getInstance()))
        {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm()
    {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent( getActivity(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast( getActivity(), 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        text11.setText("Alarm canceled");
    }









    public void start()
        {
            if (myBluetoothAdapter.isEnabled())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("關閉藍芽?");
                        builder.setPositiveButton("否", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                openBTHelperDialog();
                            }
                        });
                        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                disableBT();
                            }

                        });
                        builder.setCancelable(true);
                        builder.show();

                    }
                    else if (!myBluetoothAdapter.isEnabled())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("開啟藍芽?");
                        builder.setPositiveButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {



                            }
                        });
                        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                enableBT();
                            }

                        });
                        builder.setCancelable(true);
                        builder.show();
                    }

            }

    private void openBTHelperDialog()
    {
        BTDialog1 btDialog1 = new BTDialog1();
        btDialog1.show(getFragmentManager(),"btDialog1 dialog");
    }



}