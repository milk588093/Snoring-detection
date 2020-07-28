package com.example.project1220.ui.home;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BluetoothService
{

    private static final String TAG = "BluetoothConnection";
    private static final String appName = "project1220";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final BluetoothAdapter bluetoothAdapter;
    Context mcontext;
    private ConnectThread connectThread;
    private BluetoothDevice btDevice;
    private UUID deviceUUID;
    private ConnectedThread connectedThread;

    public BluetoothService(Context context)
    {
        context = mcontext;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        start();
    }

    private class ConnectThread extends Thread
    {

        private BluetoothSocket mySocket;

        public ConnectThread(BluetoothDevice device, UUID uuid)
        {
            Log.d(TAG, "ConnectThread: started.");
            btDevice = device;
            deviceUUID = uuid;
        }

        public void run()
        {
            BluetoothSocket tmp = null;
            Log.i(TAG, "RUN mConnectThread ");
            try
            {
                Log.d(TAG, "ConnectThread: Trying to create InsecureRfcommSocket using UUID: "
                        +MY_UUID );
                tmp = btDevice.createRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e)
            {
                Log.e(TAG, "ConnectThread: Could not create InsecureRfcommSocket " + e.getMessage());
            }

            mySocket = tmp;
            bluetoothAdapter.cancelDiscovery();
            try {
                mySocket.connect();
                Log.d(TAG, "run: ConnectThread connected.");
            } catch (IOException e)
            {
                try {
                    mySocket.close();
                    Log.d(TAG, "run: Closed Socket.");
                } catch (IOException e1) {
                    Log.e(TAG, "mConnectThread: run: Unable to close connection in socket " + e1.getMessage());
                }
                Log.d(TAG, "run: ConnectThread: Could not connect to UUID: " + MY_UUID );
                Log.d(TAG,e.getMessage());
            }

            connected(mySocket,btDevice);
        }
        public void cancel()
        {
            try
            {
                Log.d(TAG, "cancel: Closing Client Socket.");
                mySocket.close();
            } catch (IOException e)
            {
                Log.e(TAG, "cancel: close() of mmSocket in Connectthread failed. " + e.getMessage());
            }
        }
    }

    public synchronized void start()
    {
        Log.d(TAG, "start");
        if (connectThread != null)
        {
            connectThread.cancel();
            connectThread = null;
        }
    }

    public void startClient(BluetoothDevice device, UUID uuid)
    {
        Log.d(TAG, "startClient: Started.");
        Log.d(TAG,"Connecting Bluetooth, Please Wait...");
        /*progressDialog = ProgressDialog.show(mcontext,"Connecting Bluetooth"
                ,"Please Wait...",true);
*/
        connectThread = new ConnectThread(device, uuid);
        connectThread.start();

    }

    public void stopClient(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "stopClient: Started.");
        Log.d(TAG,"Disconnecting Bluetooth, Please Wait...");
        connectThread.cancel();

    }

    private class ConnectedThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        StringBuilder stringBuilder = new StringBuilder();

        public ConnectedThread(BluetoothSocket socket)
        {
            Log.d(TAG, "ConnectedThread: Starting.");
            mmSocket = socket;
            InputStream tmpIn = null;

            try
            {
                tmpIn = mmSocket.getInputStream();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            mmInStream = tmpIn;
        }

        public void run()
        {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true)
            {
                try
                {
                    bytes = mmInStream.read(buffer);
                    String incomingMessage = new String(buffer, 0, bytes);
                    stringBuilder.append(incomingMessage);
                    Log.d(TAG, "InputStream: " + incomingMessage);

                } catch (IOException e)
                {
                    Log.e(TAG, "write: Error reading Input Stream. " + e.getMessage() );
                    break;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());

            String data = stringBuilder.toString();
            String number = stringBuilder.toString();
            Log.d(TAG, "DATA: " + data);
            HomeFragment.myDb.insertData(currentDateandTime,data);

        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

    private void connected(BluetoothSocket mmSocket, BluetoothDevice mmDevice) {
        Log.d(TAG, "connected: Starting.");
        connectedThread = new ConnectedThread(mmSocket);
        connectedThread.start();
    }

}
