package com.julio.station_gas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.julio.station_gas.list_postos.Postos;
import com.julio.station_gas.list_preco_combustivel.PreCom;
import com.julio.station_gas.list_preco_combustivel.PreComAdapter;
import com.julio.station_gas.list_servicos.Service;
import com.julio.station_gas.list_servicos.ServiceAdapter;
import com.julio.station_gas.system.BD.Posto;

import java.util.ArrayList;


/**
 * Created by Julio on 14/02/2017.
 */

public class DetalhesPosto extends AppCompatActivity {
    RatingBar qualidade;
    RatingBar atendimento;
    AtualizarPosto atualizarPosto;
    ListView listCheckedServicos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_posto);
        atualizarPosto = (AtualizarPosto) this.getParent();


        iniListChekedService();
        inifuncaoChekedService();
        iniButton();
        iniRatingBar();
    }

    //---------------button proximo | voltar------------------------
    private void iniButton() {
        Button btnNext = (Button) findViewById(R.id.btn_next);
        ImageButton btnVoltar = (ImageButton) findViewById(R.id.btn_voltar);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                atualizarPosto.post.setQualiCombus(qualidade.getRating());
                atualizarPosto.post.setQualiAtendi(atendimento.getRating());
                atualizarPosto.getTabHost().setCurrentTabByTag("Atualizar preco");
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //----------------ChekedServicos---------------------
    private void iniListChekedService() {
        listCheckedServicos = (ListView) findViewById(R.id.servicos);
        ArrayList<Service> list = Service.createList(getResources().getStringArray(R.array.tipos_servicos),atualizarPosto.post);
        ServiceAdapter adapter = new ServiceAdapter(this, R.layout.checked_text_view, list);
        listCheckedServicos.setAdapter(adapter);
        listCheckedServicos.setItemsCanFocus(false);
        // we want multiple clicks
        listCheckedServicos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
    //-----------item selecionado da lista dos postos---------------
    private void inifuncaoChekedService(){
        listCheckedServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent , View view,
                                    int position , long id) {

                Service service = (Service) parent.getAdapter().getItem(position);
                atualizarPosto.post.setServicos(service.getServico(),
                        !atualizarPosto.post.getServicos(service.getServico()));
                Log.d("Lista","passou aqui"+(atualizarPosto.post.getServicos(service.getServico())));
            }
        });
    }

    //---------------------barra de estrelas -------------------------
    public void iniRatingBar(){
        qualidade = (RatingBar) findViewById(R.id.qualidade);
        atendimento = (RatingBar) findViewById(R.id.atendimento);
        qualidade.setRating(atualizarPosto.post.getQualiCombus());
        atendimento.setRating(atualizarPosto.post.getQualiAtendi());

    }

}
