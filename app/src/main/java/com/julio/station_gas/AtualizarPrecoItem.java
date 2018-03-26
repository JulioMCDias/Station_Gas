package com.julio.station_gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import static android.R.attr.tag;

public class AtualizarPrecoItem extends AppCompatActivity {
    String nome;
    float preco;
    Button btnCancelar;
    Button btnAplicar;
    TextView combustive;
    EditText precoCombustivel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco_atualizar);


        iniIntent();
        iniButton();
        iniToobar();
    }
//------------------ini intent----------------------
    private void iniIntent(){
        Bundle extras = getIntent().getExtras();
        //Log.d("Intent","teste" + preco);
        preco = extras.getFloat("PRECO",-1);

        Log.d("Intent","var" + preco);

        nome = extras.getString("NOME");
        combustive = (TextView) findViewById(R.id.nome_combustivel);
        combustive.setText(nome);
        precoCombustivel = (EditText) findViewById(R.id.preco_edit_text);
        precoCombustivel.setText(String.valueOf(preco));
    }

    //-------------------buttom Atualizar -------------------------------------
    private void iniButton(){
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAplicar = (Button) findViewById(R.id.btn_aplicar);
        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float saidaDoPreco = Float.parseFloat(precoCombustivel.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("PRECO",saidaDoPreco);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    //-------------------Toobar menu barra-------------------------------------
    private void iniToobar(){
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.preco_atualiz);
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
