package com.example.project1220.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.project1220.R;

import java.util.ArrayList;
import java.util.UUID;

public class BTDialog2 extends DialogFragment
{

    static String TAG = "SCAN";
    ListView listViewDevice2,listViewDevice3;
    static BluetoothDevice myBTDevice;
    ArrayList<BluetoothDevice> BTArrayListsFound;
    ArrayAdapter BTArrayAdapterPaired2, BTArrayAdapterFound;
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    static BluetoothService myBluetoothService;

    private BroadcastReceiver BroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                BTArrayListsFound.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                BTArrayAdapterFound.notifyDataSetChanged();
            }
        }
    };

    private void checkBTPermissions()
    {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                permissionCheck = getActivity().checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permissionCheck += getActivity().checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            }
            if (permissionCheck != 0) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
                }
            }
        }else{
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    private final BroadcastReceiver BroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();
            if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                    myBTDevice = mDevice;
                    Connect();
                    HomeFragment.connectState =1;
                   // HomeFragment.buttonStop.setEnabled(true);

                }
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                }
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                }

            }
        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BTArrayListsFound = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_scan2,null);
        listViewDevice2 = view.findViewById(R.id.listViewDevice2);
        listViewDevice3 = view.findViewById(R.id.listViewDevice3);

        BTArrayAdapterFound = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,BTArrayListsFound);
        BTArrayAdapterPaired2 = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, BTDialog1.BTArrayListsPaired);

        listViewDevice2.setAdapter(BTArrayAdapterPaired2);
        listViewDevice3.setAdapter(BTArrayAdapterFound);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        getActivity().registerReceiver(BroadcastReceiver3, filter);

        listViewDevice2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), BTDialog1.BTArrayListsPaired.get(position).toString(), Toast.LENGTH_SHORT).show();

                myBTDevice = BTDialog1.BTArrayListsPaired.get(position);
                myBluetoothService = new BluetoothService(getActivity());
                Connect();
                HomeFragment.connectState =1;
               // HomeFragment.buttonStop.setEnabled(true);
            }
        });

        listViewDevice3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createBond(position,BTArrayListsFound);
            }
        });

        scan();

        builder.setView(view)
                .setTitle("Scanning for other device...");

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void scan() {
        if(HomeFragment.myBluetoothAdapter.isDiscovering()){
            HomeFragment.myBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "Canceling discovery.");
            checkBTPermissions();

            HomeFragment.myBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getActivity().registerReceiver(BroadcastReceiver2, discoverDevicesIntent);
        }
        if(!HomeFragment.myBluetoothAdapter.isDiscovering()){
            checkBTPermissions();

            HomeFragment.myBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getActivity().registerReceiver(BroadcastReceiver2, discoverDevicesIntent);
        }
    }

    public void createBond(int positionID, ArrayList<BluetoothDevice> btdevicelist){
        HomeFragment.myBluetoothAdapter.cancelDiscovery();
        Log.d(TAG, "onItemClick: You Clicked on a device.");
        //String deviceName = BTArrayListsFound.get(positionID).getName();
        String deviceAddress = btdevicelist.get(positionID).getAddress();
        BluetoothDevice device = btdevicelist.get(positionID);

        //Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){

            if (device.getBondState()!= BluetoothDevice.BOND_BONDED) {

                Log.d(TAG, "Trying to pair with " + deviceAddress);
                btdevicelist.get(positionID).createBond();

                myBTDevice = btdevicelist.get(positionID);
                myBluetoothService = new BluetoothService(getActivity());
            }
        }
    }

    static public void Connect(){
        startConnection();
        Log.d(TAG,"device :" +myBTDevice.getName()+", "+myBTDevice.getAddress());
    }

    static public void startConnection(){
        startBTConnection(myBTDevice,MY_UUID);
    }

    static public void stopConnection(){
        stopBTConnection(myBTDevice,MY_UUID);
    }

    static public void startBTConnection(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startBTConnection: Initializing RFCOM Bluetooth Connection.");
        myBluetoothService.startClient(device,uuid);
    }

    static public void stopBTConnection(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "stopBTConnection: Disconnecting RFCOM Bluetooth Connection.");
        myBluetoothService.stopClient(device,uuid);
    }

    public void dismissDialog(){
        getFragmentManager().beginTransaction().remove(BTDialog2.this).commit();
    }
}
