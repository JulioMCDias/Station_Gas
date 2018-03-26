package com.julio.station_gas.system;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.julio.station_gas.list_postos.Postos;
import com.julio.station_gas.system.BD.Posto;
import com.julio.station_gas.system.BD.PostoControl;

import java.util.ArrayList;

/**
 * Created by Julio on 22/02/2017.
 */

public class BuscarPostos extends AppCompatActivity {
    private double longitudeGPS,latitudeGPS;
    private int qtMaxPostoList,distanciaMax;
    private String combustivel;
    private PostoControl control;
    private Context context;

    public  BuscarPostos(Context context){
        this.context = context;
        control = new PostoControl(context);
    }

    public ArrayList <Postos> listasDosPostos(String Combustivel){
        ArrayList <Postos> saidaArray = new ArrayList<Postos>();
        ArrayList<Posto> postosFull;

        control.open();

        postosFull = control.getAll();

        for (Posto p:postosFull)
        {
            Postos saida = new Postos();
            saida.setId(p.getId());
            saida.setNome(p.getNome());
            saida.setEnd(p.getEndereco());
            Log.d("lista",Combustivel);
            saida.setPreco(p.getCombustiveis(Combustivel));
            saida.setClassificacao(p.getQualiCombus());

            saidaArray.add(saida);
        }
        control.close();
        return saidaArray;
    }

    public void delete(Postos p){
        control.open();
        Posto posto = new Posto(context);
        posto.setId(p.getId());
        control.delete(posto);
        control.close();
    }
    public void open(){
        control.open();
    }
    public void close(){
        control.close();
    }


    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public void setDistanciaMax(int distanciaMax) {
        this.distanciaMax = distanciaMax;
    }

    public void setQtMaxPostoList(int qtMaxPostoList) {
        this.qtMaxPostoList = qtMaxPostoList;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public int getDistanciaMax() {
        return distanciaMax;
    }

    public int getQtMaxPostoList() {
        return qtMaxPostoList;
    }


}
