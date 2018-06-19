package mx.jccg.running.os;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 *
 */
public class BluetoothHandler extends Handler
{

    static final String TAG = BluetoothHandler.class.getName();

    private StringBuilder stringBuilder = new StringBuilder();

    /**
     *
     * @param msg
     */
    @Override
    public void handleMessage(Message msg) {
        // super.handleMessage(msg);

        if (msg.what == 0)
        {
            stringBuilder.append((String) msg.obj);              //keep appending to string until ~
            Log.i(TAG, stringBuilder.toString());
        }

    }
}
