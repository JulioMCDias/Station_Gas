package com.julio.station_gas.system.BD;

import com.julio.station_gas.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julio on 22/02/2017.
 */

public class Posto extends AppCompatActivity{
    private long id;
    private String nome, endereco;
    private float qualiAtendi, qualiCombus;
    private double longitude, latitude;
    private float[] combustiveis ;
    private boolean[] servicos;
    private String[] comb;
    private String[] serv;

    public Posto (Context mContext){
        combustiveis = new float[mContext.getResources().getStringArray(R.array.tipocombustiveis).length];
        servicos = new boolean[mContext.getResources().getStringArray(R.array.tipos_servicos).length];
        comb = mContext.getResources().getStringArray(R.array.tipocombustiveis);
        serv = mContext.getResources().getStringArray(R.array.tipos_servicos);

    }

        //---------
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setQualiAtendi(float qualiAtendi) {
        this.qualiAtendi = qualiAtendi;
    }

    public void setQualiCombus(float qualiCombus) {
        this.qualiCombus = qualiCombus;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    //--------------------------
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public float getQualiAtendi() {
        return qualiAtendi;
    }

    public float getQualiCombus() {
        return qualiCombus;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    ///-----------
    public float getCombustiveis(String combus) {
        for(int i = 0;i < comb.length ;i++){
            if(combus.equals(comb[i])){
                //Log.d("RETORNO getCombus", " " + combustiveis[i]);
                return combustiveis[i];
            }
        }
        return 0;
    }

    public boolean getServicos(String s) {
        for(int i = 0;i < serv.length ;i++){
            if(s.equals(serv[i]))
                return servicos[i];
        }
        return false;
    }

    public void setCombustiveis(String combus,float combustivel) {
        for(int i = 0;i < comb.length ;i++){
            if(combus.equals(comb[i]))
                combustiveis[i] = combustivel;
        }
    }

    public void setServicos(String s,boolean servico) {
        for(int i = 0;i < serv.length ;i++){
            if(s.equals(serv[i]))
                servicos[i] = servico;
        }
    }
}
