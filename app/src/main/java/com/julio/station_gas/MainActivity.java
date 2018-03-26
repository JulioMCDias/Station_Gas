package com.julio.station_gas;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    Button btn_ok;
    String combustivel;
    Spinner tipoCombustive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        iniToobar();
        iniButton();
        iniSpinner();
    }


    //-------------------Toobar menu barra-------------------------------------
    public void iniToobar(){
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.my_activity_bar);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setTitleTextColor(getResources().getColor(R.color.azul_escuro));
        actionBarToolBar.setNavigationIcon(R.drawable.ic_seta_voltar);
    }


    //-------------------Spinner combustivel-------------------------------------
    public void iniSpinner(){
        tipoCombustive = (Spinner) findViewById (R.id.combustivel);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipocombustiveis, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipoCombustive.setAdapter(adapter);
    }

    //-------------------buttom buscar -------------------------------------
    public void iniButton(){
       btn_ok = (Button) findViewById(R.id.btn_buscar);

       btn_ok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent buspost = new Intent(MainActivity.this, ListaPostos.class) ;
               combustivel = tipoCombustive.getSelectedItem().toString();
               buspost.putExtra("COMBUSTIVEL",combustivel);
               startActivity(buspost);
           }
       });
   }


    //-------------------carregar botoes do menu--------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    //---------------funçoes do botoes do menu-------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_btn:
                Intent tLogin = new Intent(MainActivity.this, Logar.class) ;
                startActivity(tLogin);
                break;
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}






//Habilita botão voltar.

        /*
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/


//acivity barra Toolbar
        /*
        Toolbar actionBarToolBar = (Toolbar) findViewById(R.id.my_activity_bar);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setLogo(R.drawable.ic_logo);
        actionBarToolBar.setLogoDescription(getResources().getString(R.string.btn_logar_esqueceusenha));
        actionBarToolBar.setSubtitle("subtitulo");
        actionBarToolBar.setSubtitleTextColor(getResources().getColor(R.color.azul_escuro));
        actionBarToolBar.setTitle("titulo");
        actionBarToolBar.setTitleTextColor(getResources().getColor(R.color.azul_escuro));
        actionBarToolBar.setNavigationIcon(R.drawable.ic_logar);
        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.app_name_titulo));
        actionBarToolBar.inflateMenu(R.menu.main);
*/
