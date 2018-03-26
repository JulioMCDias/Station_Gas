package com.julio.station_gas.list_preco_combustivel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.julio.station_gas.R;


import java.util.List;

/**
 * Created by Julio on 15/02/2017.
 */

public class PreComAdapter extends ArrayAdapter<PreCom> {
    private int resource;

    public PreComAdapter(Context context , int resource , List<PreCom> objects) {
        super(context ,resource,objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position , View convertView , @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(this.resource , parent , false);
        }

        PreCom preCom = getItem(position);

        TextView combustivel = (TextView) row.findViewById(R.id.item_combustivel);

        assert preCom != null;
        combustivel.setText(preCom.getCombustivel());

        TextView preco = (TextView) row.findViewById(R.id.item_preco);
        preco.setText(String.valueOf("R$: " + preCom.getPreco()));


        return row;
    }
}
