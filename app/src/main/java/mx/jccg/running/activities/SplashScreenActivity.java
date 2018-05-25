package mx.jccg.running.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Timer;
import java.util.TimerTask;

import mx.jccg.running.R;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class SplashScreenActivity extends AppCompatActivity implements MaterialDialog.SingleButtonCallback
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

        startActivity(SelectDeviceActivity.class);

//        new MaterialDialog.Builder(SplashScreenActivity.this)
//                .title(getString(R.string.title_dialog_activity_splash_screen))
//                .content(getString(R.string.content_dialog_activity_splash_screen))
//                .positiveText(getString(R.string.positive_text_dialog_activity_splash_screen))
//                .onPositive(this)
//                .cancelable(false)
//                .canceledOnTouchOutside(false)
//                .show();
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
     * @param dialog
     * @param which
     */
    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
    {
       startActivity(SelectDeviceActivity.class);
    }
}
