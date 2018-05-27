package mx.jccg.running.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import mx.jccg.running.R;
import mx.jccg.running.time.StartCountDownTimer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

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
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.time_text_view);

        startFloatingActionButton = findViewById(R.id.start_floating_action_button);
        startFloatingActionButton.setOnClickListener(this);

        startCountDownTimer = new StartCountDownTimer(timeTextView);
    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        if(START == null)
        {
            START = true;

            startCountDownTimer.start();

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#EC3951")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_stop));
            }

        } else if(START) {

            START = false;

            startCountDownTimer.start();

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#8BE559")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_play_arrow));
            }

        } else {

            START = true;

            startFloatingActionButton.setBackgroundTintList(ColorStateList
                    .valueOf(Color.parseColor("#EC3951")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startFloatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_stop));
            }

        }

    }

}
