package com.julio.station_gas;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by Julio on 04/02/2017.
 */

public class Logar extends AppCompatActivity {
    Button ok;
    Button esqueceuSenha;
    Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ok =(Button)findViewById(R.id.ok);
        esqueceuSenha = (Button)findViewById(R.id.eSenha);
        toolBar = (Toolbar) findViewById(R.id.activity_logar);

        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(getResources().getColor(R.color.cinza_titulo));
        toolBar.setNavigationIcon(R.drawable.ic_seta_voltar_cinza);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(Logar.this, new Intent(Logar.this, MainActivity.class));
            }
        });

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //voltar
        esqueceuSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
