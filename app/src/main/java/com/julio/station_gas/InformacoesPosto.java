package com.julio.station_gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.julio.station_gas.list_preco_combustivel.PreCom;
import com.julio.station_gas.list_preco_combustivel.PreComAdapter;
import com.julio.station_gas.list_servicos.Service;
import com.julio.station_gas.list_servicos.ServiceAdapter;
import com.julio.station_gas.system.BD.Posto;
import com.julio.station_gas.system.BD.PostoControl;

import java.util.ArrayList;

/**
 * Created by Julio on 15/02/2017.
 */

public class InformacoesPosto extends AppCompatActivity{
    private Button btnAtualizar;
    ListView precoCombustiveis;
    private Posto posto;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_posto);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("_IdPosto");

    }

    @Override
    protected void onResume() {
        super.onResume();
        PostoControl p = new PostoControl(this);
        p.open();
        posto = p.buscar(id);
        p.close();

        iniButton();
        iniToobar();
        iniListChekedService();
        iniListViewPrecos();
        iniNomeEndPosto();
        iniRatingBar();
    }

    // --- nome do posto e endereço ---
    private void iniNomeEndPosto(){
        TextView nomeEnd = (TextView) findViewById(R.id.nome_end);
        nomeEnd.setText(posto.getNome()+"\n" + posto.getEndereco());
    }

    //---------barra de estreas atendimento e qualidade de porduto----
    private void iniRatingBar(){
        RatingBar atendimento = (RatingBar)findViewById(R.id.estrela_atendimento);
        RatingBar qualidade = (RatingBar)findViewById(R.id.estrela_qualidade);
        atendimento.setRating(posto.getQualiAtendi());
        qualidade.setRating(posto.getQualiCombus());
    }

    //------------lista do preco Atualizar----------------------
    private void iniListViewPrecos() {
        precoCombustiveis = (ListView) findViewById(R.id.precos_combustiveis_list);


        ArrayList<PreCom> list = PreCom.createPreComList(getResources().getStringArray(R.array.tipocombustiveis),posto);

        PreComAdapter adapter = new PreComAdapter(this, R.layout.list_item_preco_detalhes, list);
        precoCombustiveis.setAdapter(adapter);

    }

    //----------------ChekedServicos---------------------
    private void iniListChekedService() {
        ListView listCheckedServicos;
        listCheckedServicos = (ListView) findViewById(R.id.servicos);
        ArrayList<Service> list = Service.createList(getResources().getStringArray(R.array.tipos_servicos),posto);
        ServiceAdapter adapter = new ServiceAdapter(this, R.layout.checked_text_view_indicador, list);

        listCheckedServicos.setAdapter(adapter);
        listCheckedServicos.setItemsCanFocus(false);
        // we want multiple clicks
        listCheckedServicos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }

    //-------------------buttom Atualizar -------------------------------------
    private void iniButton(){
        btnAtualizar = (Button) findViewById(R.id.btn_atualizar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tLogin = new Intent(InformacoesPosto.this, AtualizarPosto.class) ;
                tLogin.putExtra("_IdPosto",id);
                startActivity(tLogin);
            }
        });
    }

    //-------------------Toobar menu barra-------------------------------------
    private void iniToobar(){
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.info_posto);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setTitleTextColor(getResources().getColor(R.color.cinza_titulo));
        actionBarToolBar.setNavigationIcon(R.drawable.ic_seta_voltar_cinza);
    }


    //-------------------carregar botoes do menu--------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.padrao, menu);
        return true;
    }
    //---------------funçoes do botoes do menu-------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
