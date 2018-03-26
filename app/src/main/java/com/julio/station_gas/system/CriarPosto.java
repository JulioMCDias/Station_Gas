package com.julio.station_gas.system;

import android.content.Context;

import com.julio.station_gas.system.BD.Posto;
import com.julio.station_gas.system.BD.PostoControl;

/**
 * Created by Julio on 07/03/2017.
 */
public class CriarPosto {
    Context context;
    PostoControl control;

    public  CriarPosto(Context context){
        this.context = context;
        control = new PostoControl(context);
    }

    /**
     *
     * @param n nome do posto
     * @param e endereço do posto
     * @return id do novo posto
     */
    public long novo(String n,String e){
        long id;
        Posto post = new Posto(context);
        post.setNome(n);
        post.setEndereco(e);
        control.open();

        id = control.create(post);
        control.close();
        return id;
    }

    public void open(){
        control.open();
    }
    public void close(){
        control.close();
    }
}
