package com.julio.station_gas;

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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.julio.station_gas.list_preco_combustivel.PreComAdapter;
import com.julio.station_gas.list_preco_combustivel.PreCom;
import com.julio.station_gas.system.BD.PostoControl;

import java.util.ArrayList;

/**
 * Created by Julio on 14/02/2017.
 */


public class AtualizarPrecoList extends AppCompatActivity {
    AtualizarPosto atualizarPosto;
    ListView precoCombustiveis;
    private PreCom preCom;
    static final int CHAMADA = 1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_preco);

        atualizarPosto = (AtualizarPosto) this.getParent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        iniListViewPrecos();
        iniButton();
    }

    //---------------button proximo | voltar------------------------
    private void iniButton() {
        Button btnAplicar = (Button) findViewById(R.id.btn_aplicar);
        ImageButton btnVoltar = (ImageButton) findViewById(R.id.btn_voltar);

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostoControl w = new PostoControl(atualizarPosto);
                w.open();
                w.update(atualizarPosto.post);
                w.close();
                finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarPosto.getTabHost().setCurrentTabByTag("Detalhe do posto");

            }
        });
    }


    //-----------atualizar a lista dos preços dos combustiveis----------------
    private void atualizarPreco(){
        precoCombustiveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent , View view,
                                    int position , long id) {
                preCom = (PreCom) parent.getAdapter().getItem(position);

                Intent listas = new Intent(AtualizarPrecoList.this,AtualizarPrecoItem.class);
                listas.putExtra("PRECO",preCom.getPreco());
                listas.putExtra("NOME",preCom.getCombustivel());
                startActivityForResult(listas, CHAMADA);
            }
        });
    }

    //------------lista do preco Atualizar----------------------
    private void iniListViewPrecos() {
        precoCombustiveis = (ListView) findViewById(R.id.precos_combustiveis_list);


        ArrayList<PreCom> list = PreCom.createPreComList(getResources().getStringArray(R.array.tipocombustiveis)
                ,atualizarPosto.post);

        PreComAdapter adapter = new PreComAdapter(this, R.layout.list_item_preco, list);
        precoCombustiveis.setAdapter(adapter);
        atualizarPreco();

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tipocombustiveis));
        precoCombustiveis.setAdapter(adapter);*/

    }

    //----------------------retornar a variavel PRECO da activity -----------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHAMADA) {
            if (resultCode == RESULT_OK) {
                atualizarPosto.post.setCombustiveis(preCom.getCombustivel(),data.getFloatExtra("PRECO",-1));
                Log.d("RETORNO","var" + data.getFloatExtra("PRECO",-1));
            }
        }
    }
}









