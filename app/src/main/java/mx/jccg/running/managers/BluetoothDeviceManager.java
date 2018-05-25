package mx.jccg.running.managers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import java.util.Set;
import java.util.Timer;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public final class BluetoothDeviceManager
{

    private static BluetoothDeviceManager instance;

    private static final int REQUEST_ENABLE_BT = 1;

    private Activity activity;
    private BluetoothAdapter mBluetoothAdapter;
    private Timer timer;
    private boolean statBluetoothDeviceScan = false;

    /**
     *
     * @param activity
     */
    private BluetoothDeviceManager(@NonNull Activity activity)
    {
        this.activity = activity;

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            throw new RuntimeException("Device does not support Bluetooth");
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            this.activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        timer = new Timer();
    }

    /**
     *
     * @param activity
     * @return
     */
    public static BluetoothDeviceManager getInstance(@NonNull Activity activity)
    {
        if(instance == null)
        {
            instance = new BluetoothDeviceManager(activity);
        }

        return instance;
    }

    /**
     *
     * @return
     */
    public Set<BluetoothDevice> getBondedDevices()
    {
        return mBluetoothAdapter.getBondedDevices();
    }

    /**
     *
     */
    public void startDiscovery()
    {
        if (mBluetoothAdapter.isDiscovering())
        {
            timer.cancel();
        }

        mBluetoothAdapter.startDiscovery();

        final IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(new BluetoothDeviceBroadcastReceiver(), filter);

        timer.schedule(new BluetoothDeviceScanTimerTask(mBluetoothAdapter), 30000L);
    }

}
