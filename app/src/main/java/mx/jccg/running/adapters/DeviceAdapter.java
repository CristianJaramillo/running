package mx.jccg.running.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mx.jccg.running.R;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceViewHolder>
{

    private static final String TAG = DeviceAdapter.class.getName();

    /**
     *
     */
    private Context context;

    /**
     *
     */
    private Set<BluetoothDevice> bluetoothDevices;

    /**
     *
     * @param context
     * @param bluetoothDevices
     */
    public DeviceAdapter(@NonNull Context context, @NonNull Set<BluetoothDevice> bluetoothDevices) {
        this.context = context;
        this.bluetoothDevices = bluetoothDevices;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.device_item_recycler_view, parent, false);

        // Return a new holder instance
        return new DeviceViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position)
    {
        final List<BluetoothDevice> list = new ArrayList();

        list.addAll(bluetoothDevices);

        final BluetoothDevice bluetoothDevice = list.get(position);

        Log.i(TAG, String.format("| %20s | %17s |", bluetoothDevice.getName(), bluetoothDevice.getAddress()));

        holder.getLabelTextView().setText(bluetoothDevice.getName());
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }

    /**
     *
     * @return
     */
    public Set<BluetoothDevice> getBluetoothDevices() {
        return bluetoothDevices;
    }

    /**
     *
     * @param bluetoothDevices
     */
    public void setBluetoothDevices(Set<BluetoothDevice> bluetoothDevices) {
        this.bluetoothDevices = bluetoothDevices;
    }
}
