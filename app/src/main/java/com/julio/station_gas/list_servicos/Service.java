package com.julio.station_gas.list_servicos;

import com.julio.station_gas.system.BD.Posto;

import java.util.ArrayList;

/**
 * Created by Julio on 12/03/2017.
 */

public class Service {
    private String servico;
    private boolean possui;

    public static ArrayList<Service> createList(String ser[], Posto p){
        ArrayList<Service> saida = new ArrayList<Service>();
        for (String i : ser) {
            Service work = new Service();
            work.setServico(i);
            work.setPossui(p.getServicos(i));
            saida.add(work);
        }
        return saida;
    }

    public void setPossui(boolean possui) {
        this.possui = possui;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getServico() {
        return servico;
    }

    public boolean isPossui() {
        return possui;
    }
}
