package me.rondevu.rondevu;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Andrew on 4/11/2015.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    protected TextView eventName;
    protected TextView hostName;
    protected TextView votes;
    protected TextView category;
    protected TextView eventInfo;

    public EventViewHolder(View itemView) {
        super(itemView);

        eventInfo = (TextView) itemView.findViewById(R.id.eventInfoCard);

    }
}
