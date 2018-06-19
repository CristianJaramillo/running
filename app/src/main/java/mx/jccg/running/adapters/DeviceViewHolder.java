package mx.jccg.running.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mx.jccg.running.R;
import mx.jccg.running.activities.MainActivity;
import mx.jccg.running.managers.BluetoothDeviceManager;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public final class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    static final String TAG = DeviceAdapter.class.getName();

    /**
     *
     */
    private ImageView iconImageView;

    /**
     *
     */
    private TextView labelTextView;

    /**
     *
     * @param itemView
     */
    public DeviceViewHolder(View itemView)
    {
        super(itemView);

        itemView.setOnClickListener(this);

        iconImageView = itemView.findViewById(R.id.ic_bluetooth_image_view);
        labelTextView = itemView.findViewById(R.id.device_name_text_view);

    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        final Intent intent = new Intent(view.getContext(), MainActivity.class);

        Log.i(TAG, getLabelTextView().getText().toString());

        intent.putExtra("bluetooth_device", getLabelTextView().getText());

        view.getContext().startActivity(intent);
        ((Activity)view.getContext()).finish();
    }

    /**
     *
     * @return
     */
    public ImageView getIconImageView() {
        return iconImageView;
    }

    /**
     *
     * @param iconImageView
     */
    public void setIconImageView(ImageView iconImageView) {
        this.iconImageView = iconImageView;
    }

    /**
     *
     * @return
     */
    public TextView getLabelTextView() {
        return labelTextView;
    }

    /**
     *
     * @param labelTextView
     */
    public void setLabelTextView(TextView labelTextView) {
        this.labelTextView = labelTextView;
    }

}