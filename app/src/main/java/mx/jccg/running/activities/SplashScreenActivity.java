package mx.jccg.running.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import mx.jccg.running.R;
import mx.jccg.running.configs.RunningConfig;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class SplashScreenActivity extends AppCompatActivity
{

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
    }

    /**
     *
     */
    @Override
    protected void onStart()
    {
        super.onStart();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&  ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        {
            startActivity(SelectDeviceActivity.class);

            return;
        }

        ActivityCompat.requestPermissions(SplashScreenActivity.this, RunningConfig.getPermissions(), 1);

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
        final Intent intent = new Intent(SplashScreenActivity.this, view);

        if (bundle != null)
            intent.putExtras(bundle);

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.onStart();
    }

}