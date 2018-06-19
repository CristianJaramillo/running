package mx.jccg.running.time;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import mx.jccg.running.activities.MainActivity;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class StartCountDownTimer extends CountDownTimer
{
    static final String TAG = StartCountDownTimer.class.getName();

    static final long COUNT_DOWN_INTERVAL = 1000;
    static final long MILLIS_IN_FUTURE =  86400000;

    /**
     *
     */
    private TextView textView;

    /**
     *
     * @param textView
     */
    public StartCountDownTimer(TextView textView)
    {
        super(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL);

        this.textView = textView;
    }

    /**
     *
     * @param l
     */
    @Override
    public void onTick(long l)
    {
        if(l == -1L)
            onFinish();
        else {
            long second = ((MILLIS_IN_FUTURE - l) / COUNT_DOWN_INTERVAL) % 60;
            long minute = ((MILLIS_IN_FUTURE - l) / (COUNT_DOWN_INTERVAL * 60)) % 60;
            long hour   = ((MILLIS_IN_FUTURE - l) / (COUNT_DOWN_INTERVAL * 60 * 60)) % 24;

            textView.setText(String.format("%02d:%02d:%02d", hour, minute, second));
        }
    }

    /**
     *
     */
    @Override
    public void onFinish() {
        Log.i(TAG, "Finish");
        textView.setText("00:00:00");
    }

}
