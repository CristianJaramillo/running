package mx.jccg.running.managers;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;

import java.util.TimerTask;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class BluetoothDeviceScanTimerTask extends TimerTask
{

    private static final String TAG = BluetoothDeviceScanTimerTask.class.getName();

    private BluetoothAdapter bluetoothAdapter;

    /**
     *
     * @param bluetoothAdapter
     */
    public BluetoothDeviceScanTimerTask(BluetoothAdapter bluetoothAdapter) {
        super();
        this.bluetoothAdapter = bluetoothAdapter;
    }

    /**
     *
     */
    @Override
    public void run()
    {
        Log.i(TAG, "Finish Discovering");

        if(bluetoothAdapter.isDiscovering())
            bluetoothAdapter.cancelDiscovery();
    }

}
