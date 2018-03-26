package com.julio.station_gas;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.julio.station_gas.system.BD.Posto;
import com.julio.station_gas.system.BD.PostoControl;



/**
 * Created by Julio on 11/02/2017.
 */

public class AtualizarPosto extends TabActivity {
    private long id;
    protected Posto post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_detalhes_posto);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("_IdPosto");

        PostoControl p = new PostoControl(this);
        p.open();
        post = p.buscar(id);
        p.close();

        iniTabHost();
    }


    //----------------Aba - Detalhe do posto/Atualizar preço---------------------
    private void iniTabHost() {
        View tabView;
        Intent intent;
        TabHost host = getTabHost();
        //Tab 1
        tabView = createTabView(this, "Detalhe do posto");
        TabHost.TabSpec spec = host.newTabSpec("Detalhe do posto");
        spec.setContent(new Intent().setClass(this,DetalhesPosto.class));
        spec.setIndicator(tabView);
        host.addTab(spec);

        //Tab 2
        tabView = createTabView(this, "Atualizar preço");
        spec = host.newTabSpec("Atualizar preco");
        spec.setContent(new Intent().setClass(this,AtualizarPrecoList.class));
        spec.setIndicator(tabView);
        host.addTab(spec);
    }

    private static View createTabView(Context context, String tabText) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null, false);
        TextView tv = (TextView) view.findViewById(R.id.tabTitleText);
        tv.setText(tabText);
        return view;
    }


}
