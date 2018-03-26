package com.julio.station_gas.list_postos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.julio.station_gas.R;


import java.util.List;

/**
 * Created by Julio on 17/02/2017.
 */

public class PostosAdapter extends ArrayAdapter<Postos> {
private int resource;

    public PostosAdapter(Context context , int resource , List<Postos> objects) {
            super(context ,resource,objects);
            this.resource = resource;
            }

    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(this.resource , parent , false);
        }

        Postos postos = getItem(position);

        TextView nome = (TextView) row.findViewById(R.id.item_nome);
        nome.setText(postos.getNome());

        TextView end = (TextView) row.findViewById(R.id.item_endereco);
        end.setText(postos.getEnd());

        TextView preco = (TextView) row.findViewById(R.id.item_preco);
        preco.setText("R$: " + postos.getPreco());

        RatingBar classificacao = (RatingBar) row.findViewById(R.id.item_estrela);
        classificacao.setRating(postos.getClassificacao());

        return row;
    }
}
