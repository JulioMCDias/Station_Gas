package com.julio.station_gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.julio.station_gas.list_postos.Postos;
import com.julio.station_gas.list_postos.PostosAdapter;
import com.julio.station_gas.system.BuscarPostos;

import java.util.ArrayList;


/**
 * Created by Julio on 17/02/2017.
 */

public class ListaPostos extends AppCompatActivity {

    ListView listItem;
    BuscarPostos buscarPostos;
    private String combustivel;
    private Intent intent;
    private boolean delete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postos);
        buscarPostos = new BuscarPostos(this);
        iniIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniListViewPostos();
        iniToobar();
    }


    //------------ler informações enviadas-----
    private void iniIntent(){
        intent = getIntent();
        Bundle extras = intent.getExtras();
        combustivel = extras.getString("COMBUSTIVEL");
    }

    //------------lista dos postos---------------------
    private void iniListViewPostos() {
        listItem = (ListView) findViewById(R.id.lista_postos);

        ArrayList<Postos> list = buscarPostos.listasDosPostos(combustivel);

        //ArrayList<Postos> list = Postos.createPreComList(getResources().getStringArray(R.array.tipocombustiveis));

        PostosAdapter adapter = new PostosAdapter(this, R.layout.list_postos, list);
        listItem.setAdapter(adapter);

        itemList();

    }


    //-----------item selecionado da lista dos postos---------------
    private void itemList(){
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent , View view,
                                    int position , long id) {
                Postos postos = (Postos) parent.getAdapter().getItem(position);
                    if(delete) {
                        buscarPostos.delete(postos);
                        delete = false;
                        Toast.makeText(ListaPostos.this,"Posto deletado", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(intent);
                    }else {
                        Intent listas = new Intent(ListaPostos.this, InformacoesPosto.class);
                        listas.putExtra("_IdPosto", postos.getId());
                        startActivity(listas);
                    }
            }
        });
    }

    //-------------------Toobar menu barra-------------------------------------
    public void iniToobar(){
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.toolbar_list_postos);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setTitleTextColor(getResources().getColor(R.color.cinza_titulo));

    }


    //-------------------carregar botoes do menu--------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_posto, menu);
        return true;
    }

    //---------------funçoes do botoes do menu-------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_btn_adicionar:
                Intent tLogin = new Intent(ListaPostos.this, AdicionarPosto.class) ;
                startActivity(tLogin);
                break;
            case R.id.menu_btn_deletar:
                delete = true;
                Toast.makeText(this,"selecione o posto", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_btn_ordenar:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
