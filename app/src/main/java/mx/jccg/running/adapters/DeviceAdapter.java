package mx.jccg.running.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceViewHolder>
{


    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {

    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 0;
    }
}
