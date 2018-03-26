package com.julio.station_gas.system.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julio on 22/02/2017.
 */

public class CustomSQLiteOpenHelper extends SQLiteOpenHelper{

    public static final String TABELA_POSTOS = "postos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_END = "endereco";
    public static final String COLUMN_QUALIATEND = "qualidade_atendimento";
    public static final String COLUMN_QUALICOMBUS = "qualidade_combustivel";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_GASOLINAC = "gasolina_comun";
    public static final String COLUMN_GASOLINAP = "gasolina_premium";
    public static final String COLUMN_ETANOL = "etanol";
    public static final String COLUMN_GNV = "gnv";
    public static final String COLUMN_DIESEL = "diesel";
    public static final String COLUMN_DIESELS10 = "diesel_s10";
    public static final String COLUMN_LOJAC = "loja_conveniencia";
    public static final String COLUMN_LAVAR = "lava_rapido";
    public static final String COLUMN_TROCAOLEO = "troca_oleo";
    public static final String COLUMN_SANITARIO = "Sanitario";
    public static final String COLUMN_ABERTO24HS = "aberto_24hs";

    private static final String DATABASE_NAME = "postos.db";
    private static final int DATABASE_VERSION = 1;


    /*private static final String DATABASE_CREATE = "create table "
            + TABELA_POSTOS + "(" + COLUMN_ID
            + " integer primary key autoincrement , " + COLUMN_NOME
            + " text not null);";*/



    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABELA_POSTOS + "(" + COLUMN_ID
            + " integer primary key autoincrement , " + COLUMN_NOME
            + " text not null, "+ COLUMN_END + " text not null, " + COLUMN_QUALIATEND
            + " REAL, "+COLUMN_QUALICOMBUS +" REAL, "+COLUMN_LONGITUDE
            + " REAL, "+COLUMN_LATITUDE+ " REAL, " +COLUMN_GASOLINAC
            + " REAL, "+COLUMN_GASOLINAP+" REAL, "+COLUMN_ETANOL
            + " REAL, "+COLUMN_GNV+" REAL, "+COLUMN_DIESEL
            + " REAL, "+COLUMN_DIESELS10+" REAL, "+COLUMN_LOJAC
            + " INTEGER, "+COLUMN_LAVAR+" INTEGER, "+COLUMN_TROCAOLEO
            + " INTEGER, "+COLUMN_SANITARIO+" INTEGER, "+COLUMN_ABERTO24HS+" INTEGER);";



    public CustomSQLiteOpenHelper(Context context) {
        super(context , DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_POSTOS);
        onCreate(db);
    }
}
