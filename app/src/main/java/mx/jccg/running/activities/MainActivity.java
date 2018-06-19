package mx.jccg.running.activities;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import mx.jccg.running.R;
import mx.jccg.running.managers.BluetoothDeviceManager;
import mx.jccg.running.os.BluetoothAsyncTask;
import mx.jccg.running.os.BluetoothHandler;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    /**
     *
     */
    private static final String TAG = MainActivity.class.getName();

    /**
     *
     */
    private TextView distanceTextView;

    /**
     *
     */
    private TextView timeTextView;

    /**
     *
     */
    private TextView bpmTextView;

    /**
     *
     */
    private TextView thermometerTextView;

    /**
     *
     */
    private FloatingActionButton startFloatingActionButton;

    /**
     *
     */
    private BluetoothDevice bluetoothDevice;

    /**
     *
     */
    private BluetoothAsyncTask bluetoothAsyncTask;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distanceTextView = findViewById(R.id.distance_text_view);
        timeTextView = findViewById(R.id.time_text_view);

        bpmTextView = findViewById(R.id.bpm_text_view);
        thermometerTextView = findViewById(R.id.thermometer_text_view);

        startFloatingActionButton = findViewById(R.id.start_floating_action_button);
        startFloatingActionButton.setOnClickListener(this);

        bluetoothDevice = BluetoothDeviceManager
                .getInstance(this)
                .findBluetoothDevice(getIntent()
                        .getStringExtra("device_name"));

        Log.i(TAG, String.format("%s - %s", bluetoothDevice.getName(), bluetoothDevice.getAddress()));

       bluetoothAsyncTask = new BluetoothAsyncTask(this, bluetoothDevice, thermometerTextView, bpmTextView);
    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(TAG, "START");
        if(bluetoothAsyncTask.getStatus() == AsyncTask.Status.PENDING)
            bluetoothAsyncTask.execute(thermometerTextView, bpmTextView);
    }

    /**
     *
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG, "RESUME");
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "PAUSE");
    }

    /**
     *
     */
    @Override
    protected void onStop() {
        super.onStop();
       Log.i(TAG, "Stop");
    }

    /**
     *
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "RESTART");
    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {

    }


}
