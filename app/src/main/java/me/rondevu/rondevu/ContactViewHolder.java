package me.rondevu.rondevu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Andrew on 4/11/2015.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    protected TextView vName;
    protected TextView vSurname;
    protected TextView vEmail;
    protected TextView vTitle;

    public ContactViewHolder(View v) {
        super(v);
        vTitle = (TextView) v.findViewById(R.id.title);
    }
}
