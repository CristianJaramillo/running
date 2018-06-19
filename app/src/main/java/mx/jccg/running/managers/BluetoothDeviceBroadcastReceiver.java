package mx.jccg.running.managers;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import mx.jccg.running.adapters.DeviceAdapter;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class BluetoothDeviceBroadcastReceiver extends BroadcastReceiver
{

    private static final String TAG = BluetoothDeviceBroadcastReceiver.class.getName();

    private DeviceAdapter deviceAdapter;

    /**
     *
     * @param deviceAdapter
     */
    public BluetoothDeviceBroadcastReceiver(DeviceAdapter deviceAdapter) {
        this.deviceAdapter = deviceAdapter;
    }

    /**
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        final String action = intent.getAction();
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Get the BluetoothDevice object from the Intent
            final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            BluetoothDeviceManager.getInstance((Activity) context).addBluetoothDevice(device);
            deviceAdapter.getBluetoothDevices().add(device);
            deviceAdapter.notifyDataSetChanged();

        }
    }

}
