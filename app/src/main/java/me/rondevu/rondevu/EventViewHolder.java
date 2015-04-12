package me.rondevu.rondevu;

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

    public EventViewHolder(View itemView) {
        super(itemView);
        eventName = (TextView) itemView.findViewById(R.id.eventTitleCard);
        hostName = (TextView) itemView.findViewById(R.id.hostNameCard);

    }
}
