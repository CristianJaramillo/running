package mx.jccg.running.activities;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mx.jccg.running.R;
import mx.jccg.running.managers.BluetoothDeviceManager;

public class SelectDeviceActivity extends AppCompatActivity {


    private static final String TAG = SelectDeviceActivity.class.getName();

    private BluetoothDeviceManager bluetoothDeviceManager;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();

        bluetoothDeviceManager = BluetoothDeviceManager.getInstance(this);

        for (BluetoothDevice bluetoothDevice : bluetoothDeviceManager.getBondedDevices())
            Log.i(TAG, String.format("| %20s | %17s |", bluetoothDevice.getName(), bluetoothDevice.getAddress()));

        bluetoothDeviceManager.startDiscovery();

    }
}
