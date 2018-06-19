package mx.jccg.running.os;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

import mx.jccg.running.managers.BluetoothDeviceManager;

public class BluetoothAsyncTask extends AsyncTask<TextView, Double, BluetoothDevice>
{

    static final String TAG = BluetoothAsyncTask.class.getName();

    private final Activity activity;
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    private TextView thermometer;
    private TextView bpm;

    /**
     *
     * @param activity
     * @param bluetoothDevice
     */
    public BluetoothAsyncTask(Activity activity, BluetoothDevice bluetoothDevice, TextView thermometer, TextView bpm)
    {
        this.activity = activity;
        this.thermometer = thermometer;
        this.bpm = bpm;
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = bluetoothDevice;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) { }
        mmSocket = tmp;

    }

    /**
     *
     * @param textViews
     * @return
     */
    @Override
    protected BluetoothDevice doInBackground(TextView... textViews)
    {

        // Cancel discovery because it will slow down the connection
        BluetoothDeviceManager.getInstance(activity).getmBluetoothAdapter().cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();

            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (mmSocket.isConnected()) {
                try {
                    bytes = mmSocket.getInputStream().read(buffer);

                    String content = new String(buffer);

                    for (String line : content.split("\n"))
                    {
                        String[] values = line.split(",");

                        if(values.length == 2)
                        {
                            Double thermometer;
                            Double bpm;

                            try {
                                thermometer = Double.valueOf(values[0]);
                                bpm = Double.valueOf(values[1]);
                                publishProgress(thermometer, bpm);
                            } catch (NumberFormatException nfe){
                                Log.e(TAG, nfe.getMessage());
                            }

                        }

                        onProgressUpdate();
                    }

                } catch (IOException e) {
                    break;
                }
            }

        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            Log.e(TAG, connectException.getMessage());
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, closeException.getMessage());
            }
        }


        return mmDevice;
    }

    /**
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Double... values)
    {
        super.onProgressUpdate(values);

        if(values.length == 2)
        {
            thermometer.setText(String.format("%03d", values[0].intValue()));
            bpm.setText(String.format("%03d", values[1].intValue()));
        }

    }

}
