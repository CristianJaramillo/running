package mx.jccg.running.activities;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import mx.jccg.running.R;
import mx.jccg.running.adapters.DeviceAdapter;
import mx.jccg.running.managers.BluetoothDeviceManager;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class SelectDeviceActivity extends AppCompatActivity implements View.OnClickListener
{


    private static final String TAG = SelectDeviceActivity.class.getName();

    /**
     *
     */
    private BluetoothDeviceManager bluetoothDeviceManager;

    /**
     *
     */
    private DeviceAdapter deviceAdapterPaired;

    /**
     *
     */
    private DeviceAdapter deviceAdapter;

    /**
     *
     */
    private RecyclerView recyclerViewDevicePaired;

    /**
     *
     */
    private RecyclerView recyclerViewDevice;

    /**
     *
     */
    private FloatingActionButton floatingActionButton;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

        recyclerViewDevicePaired = findViewById(R.id.device_paired_recycler_view);
        recyclerViewDevicePaired.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDevice = findViewById(R.id.device_recycler_view);
        recyclerViewDevice.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.search_device_floating_action_button);
        floatingActionButton.setOnClickListener(this);

        bluetoothDeviceManager = BluetoothDeviceManager.getInstance(this);

    }

    /**
     *
     */
    @Override
    protected void onResume()
    {

        super.onResume();

        if(deviceAdapterPaired == null)
        {
            deviceAdapterPaired = new DeviceAdapter(this, bluetoothDeviceManager.getBondedDevices());
            deviceAdapterPaired.notifyDataSetChanged();
            recyclerViewDevicePaired.setAdapter(deviceAdapterPaired);
        } else {
            deviceAdapterPaired.setBluetoothDevices(bluetoothDeviceManager.getBondedDevices());
            deviceAdapterPaired.notifyDataSetChanged();
            recyclerViewDevicePaired.setAdapter(deviceAdapterPaired);
        }

    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();

        for (BluetoothDevice bluetoothDevice : bluetoothDeviceManager.getBondedDevices())
            Log.i(TAG, String.format("| %20s | %17s |", bluetoothDevice.getName(), bluetoothDevice.getAddress()));

        bluetoothDeviceManager.startDiscovery();

    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        startActivity(MainActivity.class);
    }

    /**
     *
     * @param view
     */
    private void startActivity(Class<?> view)
    {
        startActivity(view, null);
    }

    /**
     *
     * @param view
     * @param bundle
     */
    private void startActivity(Class<?> view, Bundle bundle)
    {
        final Intent intent = new Intent(SelectDeviceActivity.this, view);

        if (bundle != null)
            intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }

}
