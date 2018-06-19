package mx.jccg.running.activities;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.UUID;

import mx.jccg.running.R;
import mx.jccg.running.listeners.LocationOnSuccessListener;
import mx.jccg.running.managers.BluetoothDeviceManager;
import mx.jccg.running.time.StartCountDownTimer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = MainActivity.class.getName();

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
    private FloatingActionButton startFloatingActionButton;

    /**
     *
     */
    private StartCountDownTimer startCountDownTimer;

    /**
     *
     */
    public static Boolean START = null;

    /**
     *
     */
    private FusedLocationProviderClient fusedLocationProviderClient;

    /**
     *
     */
    private LocationRequest mLocationRequest;

    /**
     *
     */
    private Location currentLocation;

    /**
     *
     */
    private float distance;

    /**
     *
     */
    private long UPDATE_INTERVAL = 1000;  /* 10 secs */

    /**
     *
     */
    private long FASTEST_INTERVAL = 1000; /* 2 sec */

    /**
     *
     */
    private BluetoothDevice bluetoothDevice;

    /**
     *
     */
    private ConnectThread connectThread;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distanceTextView = findViewById(R.id.distance_text_view);
        timeTextView = findViewById(R.id.time_text_view);

        startFloatingActionButton = findViewById(R.id.start_floating_action_button);
        startFloatingActionButton.setOnClickListener(this);

        startCountDownTimer = new StartCountDownTimer(timeTextView);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        bluetoothDevice = BluetoothDeviceManager.getInstance(this).findBluetoothDevice(getIntent().getStringExtra("device_name"));

        connectThread = new ConnectThread(bluetoothDevice);
    }

    // Trigger new location updates at interval
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            onLocationChanged(locationResult.getLastLocation());
                        }
                    },
                    Looper.myLooper());
        }

    }

    /**
     *
     * @param location
     */
    private void onLocationChanged(Location location) {
        // New location has now been determined
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());

        if (Boolean.TRUE.equals(START)) {

            distance += currentLocation.distanceTo(location);

            int m = (int) distance;
            int km = m / 1000;

            Log.i(TAG, String.format("distance %f", distance));
            Log.i(TAG, String.format("mestros %d", m));
            Log.i(TAG, String.format("kilometros %d", km));

            distanceTextView.setText(String.format("%02d.%03d", km, m));
        }

        currentLocation = location;

        Log.i(TAG, msg);
        // You can now create a LatLng Object for use with maps
    }

    /**
     *
     */
    @Override
    protected void onStart() {
        super.onStart();

        startLocationUpdates();

        connectThread.run();

    }


    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (START == null) {

            START = true;

            distance = 0;

            startCountDownTimer.start();

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#EC3951")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_stop));
            }

        } else if (START) {

            START = false;

            startCountDownTimer.start();

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#8BE559")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_play_arrow));
            }

        } else {

            START = true;

            distance = 0;

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#EC3951")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_stop));
            }

        }

    }

    private class ConnectThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            BluetoothDeviceManager.getInstance(MainActivity.this).getmBluetoothAdapter().cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            /// manageConnectedSocket(mmSocket);
        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

}
