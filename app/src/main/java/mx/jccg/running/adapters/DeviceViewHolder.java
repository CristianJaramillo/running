package mx.jccg.running.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mx.jccg.running.R;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public final class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

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




