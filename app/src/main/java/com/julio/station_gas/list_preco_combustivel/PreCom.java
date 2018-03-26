package com.julio.station_gas.list_preco_combustivel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.julio.station_gas.AtualizarPosto;
import com.julio.station_gas.R;
import com.julio.station_gas.system.BD.Posto;

/**
 * Created by Julio on 15/02/2017.
 */

public class PreCom {
    private String combustivel;
    private float preco;

    public static ArrayList<PreCom> createPreComList(String arg1[], Posto p){
        ArrayList<PreCom> list = new ArrayList<PreCom>();

        for(int position = 0;position < arg1.length ;position++){
            list.add(PreCom.carregarItem(arg1[position], p.getCombustiveis(arg1[position])));
       }
        return list;
    }

    private static PreCom carregarItem (String combustivel, float preco){
        PreCom preCom = new PreCom();
        preCom.setCombustivel(combustivel);
        preCom.setPreco(preco);
        return preCom;
    }


    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
