package com.julio.station_gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.julio.station_gas.system.CriarPosto;

/**
 * Created by Julio on 04/03/2017.
 */

public class AdicionarPosto extends AppCompatActivity {
    Button btn_adicionar,btn_cacelar;
    EditText text_endereco,text_nome;

    CriarPosto criarPosto;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_posto);
        criarPosto = new CriarPosto(AdicionarPosto.this);
        iniButton();
        iniTextView();
    }

    //------------------------ edit text --------------------
    private void iniTextView(){
        text_endereco = (EditText) findViewById(R.id.enderecoPosto);
        text_nome = (EditText) findViewById(R.id.nomePosto);
    }



    //-------------------buttom  -------------------------------------
    private void iniButton(){
        btn_adicionar = (Button) findViewById(R.id.btn_aplicar);
        btn_cacelar = (Button) findViewById(R.id.btn_cancelar);

        //---cancelar
        btn_cacelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        //---novo posto --
        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adicionarpos = new Intent(AdicionarPosto.this, AtualizarPosto.class);
                final long id = criarPosto.novo(text_nome.getEditableText().toString(),text_endereco.getEditableText().toString());
                adicionarpos.putExtra("_IdPosto",id);
                startActivity(adicionarpos);
                finish();
            }
        });
    }
}
