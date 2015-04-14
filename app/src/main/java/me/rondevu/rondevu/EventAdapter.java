package me.rondevu.rondevu;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Andrew on 4/11/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


   public List<Event> eventList;

    private static String info;
    private static Event ev2;


    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Event getEvent(int position) {
        return eventList.get(position);
    }

    public List<Event> getList() {
        return eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int i) {

        Event ev = eventList.get(i);


        eventViewHolder.eventInfo.setText(ev.toString());

        if (eventViewHolder.eventInfo.getText().toString().contains("CONCERT")) {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#4CAF50"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

        else if (eventViewHolder.eventInfo.getText().toString().contains("EVENT")) {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#5D8AA8"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

        else if (eventViewHolder.eventInfo.getText().toString().contains("FESTIVAL")) {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#AA66CC"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

        else if (eventViewHolder.eventInfo.getText().toString().contains("PARTY")) {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#FF4444"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

        else if (eventViewHolder.eventInfo.getText().toString().contains("SPORTS")) {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#99CC00"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

        else {
            eventViewHolder.eventInfo.setBackgroundColor(Color.parseColor("#33B5E5"));
            eventViewHolder.eventInfo.setTextColor(Color.WHITE);
        }

    }



    public void addAll(List<Event> addArray) {
        for(Event event : addArray) {
            eventList.add(event);
        }
    }

    public void clear() {
        eventList.clear();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new EventViewHolder(itemView);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        protected TextView eventInfo;

        public EventViewHolder(View v) {
            super(v);

            eventInfo = (TextView) v.findViewById(R.id.eventInfoCard);
        }
    }
}
