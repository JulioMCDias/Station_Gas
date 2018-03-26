package com.julio.station_gas.list_servicos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.julio.station_gas.R;

import java.util.List;

/**
 * Created by Julio on 12/03/2017.
 */

public class ServiceAdapter extends ArrayAdapter<Service>{
    private int resource;

    /**
     * @param context
     * @param resource
     * @param objects
     */

    public ServiceAdapter(Context context, int resource, List<Service> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position , View convertView , @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(this.resource, parent, false);
        }

        Service serv = getItem(position);

        CheckedTextView ser = (CheckedTextView) row.findViewById(R.id.checkedTextView);

        assert serv != null;
        ser.setText(serv.getServico());
        ((ListView) parent).setItemChecked(position, serv.isPossui());

        return row;

    }

}
