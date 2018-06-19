package mx.jccg.running.listeners;

import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class LocationOnSuccessListener implements OnSuccessListener<Location>
{

    static final String TAG = LocationOnSuccessListener.class.getName();

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
    private TextView distanceTextView;


    /**
     *
     * @param distanceTextView
     */
    public LocationOnSuccessListener(TextView distanceTextView)
    {
        this.distanceTextView = distanceTextView;
        currentLocation = null;
        distance = 0;
    }

    /**
     *
     * @param location
     */
    @Override
    public void onSuccess(Location location)
    {
        if (location != null)
        {
            if(currentLocation != null)
            {
                distance = currentLocation.distanceTo(location);
                Log.i(TAG, String.valueOf(distance));
            }

            currentLocation = location;
        }
    }

}
