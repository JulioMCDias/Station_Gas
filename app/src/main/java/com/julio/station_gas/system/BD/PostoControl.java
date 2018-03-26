package com.julio.station_gas.system.BD;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.julio.station_gas.R;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Julio on 22/02/2017.
 */

public class PostoControl extends AppCompatActivity {
    private SQLiteDatabase database;
    private Context context;
    private String[] columns = { CustomSQLiteOpenHelper.COLUMN_ID,
                                    CustomSQLiteOpenHelper.COLUMN_NOME,
                                    CustomSQLiteOpenHelper.COLUMN_END,
                                    CustomSQLiteOpenHelper.COLUMN_QUALIATEND,
                                    CustomSQLiteOpenHelper.COLUMN_QUALICOMBUS,
                                    CustomSQLiteOpenHelper.COLUMN_LONGITUDE,
                                    CustomSQLiteOpenHelper.COLUMN_LATITUDE,
                                    CustomSQLiteOpenHelper.COLUMN_GASOLINAC,
                                    CustomSQLiteOpenHelper.COLUMN_GASOLINAP,
                                    CustomSQLiteOpenHelper.COLUMN_ETANOL,
                                    CustomSQLiteOpenHelper.COLUMN_GNV,
                                    CustomSQLiteOpenHelper.COLUMN_DIESEL,
                                    CustomSQLiteOpenHelper.COLUMN_DIESELS10,
                                    CustomSQLiteOpenHelper.COLUMN_LOJAC,
                                    CustomSQLiteOpenHelper.COLUMN_LAVAR,
                                    CustomSQLiteOpenHelper.COLUMN_TROCAOLEO,
                                    CustomSQLiteOpenHelper.COLUMN_SANITARIO,
                                    CustomSQLiteOpenHelper.COLUMN_ABERTO24HS};
    private CustomSQLiteOpenHelper sqliteOpenHelper;

    public PostoControl(Context context) {
        this.context = context;
        sqliteOpenHelper = new CustomSQLiteOpenHelper(context);
    }
    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqliteOpenHelper.close();
    }



    //---------------novo registro ---------------------------
    public long create(Posto posto) {
         return database.insert(CustomSQLiteOpenHelper.TABELA_POSTOS , null,
                carregaValores(posto));

    }

    //---------------carregar values --------
    private ContentValues carregaValores(Posto p){
        ContentValues values = new ContentValues();
        values.put(CustomSQLiteOpenHelper.COLUMN_NOME , p.getNome());
        values.put(CustomSQLiteOpenHelper.COLUMN_END , p.getEndereco());
        values.put(CustomSQLiteOpenHelper.COLUMN_QUALIATEND , p.getQualiAtendi());
        values.put(CustomSQLiteOpenHelper.COLUMN_QUALICOMBUS , p.getQualiCombus());
        values.put(CustomSQLiteOpenHelper.COLUMN_LONGITUDE , p.getLongitude());
        values.put(CustomSQLiteOpenHelper.COLUMN_LATITUDE , p.getLatitude());
        values.put(CustomSQLiteOpenHelper.COLUMN_GASOLINAC , p.getCombustiveis("Gasolina Comun"));
        values.put(CustomSQLiteOpenHelper.COLUMN_GASOLINAP , p.getCombustiveis("Gasolina Aditivada"));
        values.put(CustomSQLiteOpenHelper.COLUMN_ETANOL , p.getCombustiveis("Etanol"));
        values.put(CustomSQLiteOpenHelper.COLUMN_GNV , p.getCombustiveis("GNV"));
        values.put(CustomSQLiteOpenHelper.COLUMN_DIESEL , p.getCombustiveis("Diesel"));
        values.put(CustomSQLiteOpenHelper.COLUMN_DIESELS10 , p.getCombustiveis("Diesel S10"));

        values.put(CustomSQLiteOpenHelper.COLUMN_LOJAC , convetBoolInt(p.getServicos("Loja de conveniências")));
        values.put(CustomSQLiteOpenHelper.COLUMN_LAVAR , convetBoolInt(p.getServicos("Lava-Rapido")));
        values.put(CustomSQLiteOpenHelper.COLUMN_TROCAOLEO , convetBoolInt(p.getServicos("Troca de óleo")));
        values.put(CustomSQLiteOpenHelper.COLUMN_SANITARIO , convetBoolInt(p.getServicos("Sanitário")));
        values.put(CustomSQLiteOpenHelper.COLUMN_ABERTO24HS , convetBoolInt(p.getServicos("Aberto 24hs")));
        return values;
    }

    private int convetBoolInt(boolean e){
        int saida;
        saida = (e == TRUE)? 1:0;
        return saida;
    }


    //-----------------apagar registro --------------------------------
    public void delete(Posto posto) {
        long id = posto.getId();
        database.delete(CustomSQLiteOpenHelper.TABELA_POSTOS , CustomSQLiteOpenHelper.COLUMN_ID
                + " = " + id, null);
    }


    //--------------------atualizar registro -----------------------------
    public void update(Posto posto){
        long id = posto.getId();
        database.update(CustomSQLiteOpenHelper.TABELA_POSTOS , carregaValores(posto)
                ,CustomSQLiteOpenHelper.COLUMN_ID + " = " + id,null);
    }

    //-------------------- buscar todos os registros ----------------------
    public ArrayList<Posto> getAll() {
        ArrayList<Posto> postos = new ArrayList <Posto>();

        Cursor cursor = database.query(CustomSQLiteOpenHelper.TABELA_POSTOS ,
                columns , null, null, null, null, null);

        cursor.moveToFirst();
       while (!cursor.isAfterLast()) {
            Posto posto = new Posto(context);

            int j=0;
            posto.setId(cursor.getLong(j++));
            posto.setNome(cursor.getString(j++));
            posto.setEndereco(cursor.getString(j++));
            posto.setQualiAtendi(cursor.getFloat(j++));
            posto.setQualiCombus(cursor.getFloat(j++));
            posto.setLongitude(cursor.getDouble(j++));
            posto.setLatitude(cursor.getDouble(j++));

            String comb[] = context.getResources().getStringArray(R.array.tipocombustiveis);
            for(int i = 0;i < comb.length ;i++) {
                posto.setCombustiveis(comb[i],cursor.getFloat(j++));
            }

            String serv[] = context.getResources().getStringArray(R.array.tipos_servicos);
            for(int i = 0;i < serv.length ;i++) {
                posto.setServicos(serv[i],((cursor.getInt(j++) == 1)?  TRUE : FALSE));
            }
            postos.add(posto);
            cursor.moveToNext();
        }
        cursor.close();
        return postos;
    }

    public Posto buscar(long id){
        Posto p = new Posto(context);

        Cursor cursor = database.query(CustomSQLiteOpenHelper.TABELA_POSTOS,
                columns, CustomSQLiteOpenHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        int j = 0;
        p.setId(cursor.getLong(j++));
        p.setNome(cursor.getString(j++));
        p.setEndereco(cursor.getString(j++));
        p.setQualiAtendi(cursor.getFloat(j++));
        p.setQualiCombus(cursor.getFloat(j++));
        p.setLongitude(cursor.getDouble(j++));
        p.setLatitude(cursor.getDouble(j++));

        String comb[] = context.getResources().getStringArray(R.array.tipocombustiveis);
        for (int i = 0; i < comb.length; i++) {
            p.setCombustiveis(comb[i], cursor.getFloat(j++));
        }

        String serv[] = context.getResources().getStringArray(R.array.tipos_servicos);
        for (int i = 0; i < serv.length; i++) {
            p.setServicos(serv[i], ((cursor.getInt(j++) == 1) ? TRUE : FALSE));
        }
        cursor.close();
        return p;
    }

}
