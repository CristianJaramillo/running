package mx.jccg.running.configs;

import android.Manifest;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class RunningConfig
{

    /**
     *
     */
    private static final String[] PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     *
     */
    public RunningConfig()
    {
        throw new AssertionError();
    }

    /**
     *
     * @return
     */
    public static String[] getPermissions() {
        return PERMISSIONS.clone();
    }

}
