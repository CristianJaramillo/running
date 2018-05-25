package mx.jccg.running.managers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class BluetoothDeviceBroadcastReceiver extends BroadcastReceiver
{

    private static final String TAG = BluetoothDeviceBroadcastReceiver.class.getName();
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
            // Add the name and address to an array adapter to show in a ListView
            Log.i(TAG, String.format("Device Name %s ", device.getName()));
        }
    }

}
