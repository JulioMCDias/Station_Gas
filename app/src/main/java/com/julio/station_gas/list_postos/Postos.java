package com.julio.station_gas.list_postos;

import java.util.ArrayList;

/**
 * Created by Julio on 17/02/2017.
 */

public class Postos {
    private long id;
    private String nome,end;
    private float preco,classificacao;



    public static ArrayList<Postos> createPreComList(String arg1[]){
        ArrayList<Postos> list = new ArrayList<Postos>();

        for(int position = 0;position < arg1.length ;position++){
            Postos postos = new Postos();
            postos.setNome(arg1[position]);
            postos.setPreco(position);
            list.add(postos);
            //list.add(carregarItem(arg1[position],position));
        }
        return list;
    }

    private static Postos carregarItem (String nome, float preco){
        Postos postos = new Postos();
        postos.setNome(nome);
        postos.setPreco(preco);
        return postos;
    }


    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public float getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getEnd() {
        return end;
    }


}
